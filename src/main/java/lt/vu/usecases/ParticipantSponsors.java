package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Participant;
import lt.vu.entities.Sponsor;
import lt.vu.persistence.ParticipantsDAO;
import lt.vu.persistence.SponsorsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Model
public class ParticipantSponsors implements Serializable {

    @Inject
    private ParticipantsDAO participantsDAO;

    @Getter @Setter
    private Sponsor sponsorToCreate = new Sponsor();

    @Getter @Setter
    private Participant participant;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long participantId = Long.parseLong(requestParameters.get("participantId"));
        this.participant = participantsDAO.findOne(participantId);
    }
}
