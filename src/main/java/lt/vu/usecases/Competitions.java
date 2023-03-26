package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Competition;
import lt.vu.persistence.CompetitionsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Competitions {

    @Inject
    private CompetitionsDAO competitionsDAO;

    @Getter @Setter
    private Competition competitionToCreate = new Competition();

    @Getter
    private List<Competition> allTeams;

    @PostConstruct
    public void init(){
        loadAllCompetitions();
    }

    @Transactional
    public void createCompetition(){
        this.competitionsDAO.persist(competitionToCreate);
    }

    private void loadAllCompetitions(){
        this.allTeams = competitionsDAO.loadAll();
    }
}
