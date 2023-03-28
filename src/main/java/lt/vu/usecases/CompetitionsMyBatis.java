package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.CompetitionMapper;
import lt.vu.mybatis.model.Competition;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CompetitionsMyBatis {
    @Inject
    private CompetitionMapper competitionMapper;

    @Getter
    private List<Competition> allCompetitions;

    @Getter @Setter
    private Competition competitionToCreate = new Competition();

    @PostConstruct
    public void init() {
        this.loadAllCompetitions();
    }

    private void loadAllCompetitions() {
        this.allCompetitions = competitionMapper.selectAll();
    }

    @Transactional
    public String createCompetition() {
//        competitionToCreate.setId((long) (Math.random() * 1000000));
        competitionMapper.insert(competitionToCreate);
        return "/myBatis/competitions?faces-redirect=true";
    }
}
