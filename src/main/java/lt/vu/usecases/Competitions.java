package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Competition;
import lt.vu.entities.Participant;
import lt.vu.entities.Sponsor;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CompetitionsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Model
public class Competitions {

    @Inject
    private CompetitionsDAO competitionsDAO;

    @Getter @Setter
    private Competition competitionToCreate = new Competition();

    @Getter
    private List<Competition> allTeams;

    @Getter
    private List<Integer> competitionSponsorCounts = new ArrayList<>();

    @PostConstruct
    public void init(){
        loadAllCompetitions();
    }

    @Transactional
    public void createCompetition(){
        this.competitionsDAO.persist(competitionToCreate);
    }

    @LoggedInvocation
    private void loadAllCompetitions(){
        this.allTeams = competitionsDAO.loadAll();
    }
}
