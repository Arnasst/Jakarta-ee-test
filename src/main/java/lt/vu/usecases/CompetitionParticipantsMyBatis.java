package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.CompetitionMapper;
import lt.vu.mybatis.dao.ParticipantMapper;
import lt.vu.mybatis.model.Competition;
import lt.vu.mybatis.model.Participant;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class CompetitionParticipantsMyBatis {
    @Inject
    private ParticipantMapper participantMapper;

    @Inject
    private CompetitionMapper competitionMapper;

    @Getter
    private List<Participant> participants;

    @Getter
    private Competition competition;


    @PostConstruct
    public void init() {

        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long competitionId = Long.parseLong(requestParameters.get("competitionId"));
        this.competition = competitionMapper.selectByPrimaryKey(competitionId);
        this.participants = participantMapper.selectByCompetitionId(competitionId);
    }

//    private void loadAllCompetitions() {
//        this.allCompetitions = competitionMapper.selectAll();
//    }
}
