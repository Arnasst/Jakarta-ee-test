package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Competition;
import lt.vu.entities.Participant;
import lt.vu.entities.Sponsor;
import lt.vu.persistence.CompetitionsDAO;
import lt.vu.persistence.ParticipantsDAO;
import lt.vu.persistence.SponsorsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.mail.Part;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

@Model
public class CompetitionParticipants implements Serializable {

    @Inject
    private CompetitionsDAO competitionsDAO;

    @Inject
    private ParticipantsDAO participantsDAO;

    @Inject
    private SponsorsDAO sponsorsDAO;

    @Getter @Setter
    private Competition competition;

    @Getter @Setter
    private Participant participantToCreate = new Participant();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long competitionId = Long.parseLong(requestParameters.get("competitionId"));
        this.competition = competitionsDAO.findOne(competitionId);
    }

    @Transactional
    public void createParticipant() {
        participantToCreate.setName("Participant " + (int)(Math.random() * 1000));
        // Assign a few sponsors to participant
        List<Sponsor> sponsors = getSponsors();
        participantToCreate.setSponsors(sponsors);
        // Print participant info
        System.out.println("Creating participant: " + participantToCreate.getName() + " " + participantToCreate.getId());
        participantToCreate.setCompetition(this.competition);
        participantsDAO.persist(participantToCreate);
    }

    @Transactional
    private List<Sponsor> getSponsors() {
        List<String> sponsorNames = List.of("Nike", "Adidas", "Puma", "Reebok", "Under Armour", "New Balance", "Apple");
        HashSet<Sponsor> sponsorList = new HashSet<Sponsor>();
        for (int i = 0; i < 2; i++) {
            String newSponsorName = sponsorNames.get((int)(Math.random() * sponsorNames.size()));
            Sponsor newSponsor;
            try {
                newSponsor = sponsorsDAO.findByName(newSponsorName);
                sponsorList.add(newSponsor);
            } catch (Exception e) {
                System.out.println("Sponsor with name " + newSponsorName + " not found, adding it");
                newSponsor = new Sponsor();
                newSponsor.setName(newSponsorName);
                sponsorsDAO.persist(newSponsor);
            }
            sponsorList.add(newSponsor);
        }
        return sponsorList.stream().toList();
    }
}
