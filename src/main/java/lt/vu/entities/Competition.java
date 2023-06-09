package lt.vu.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Competition.findAll", query = "select t from Competition as t"),
        @NamedQuery(name = "Competition.findOne", query = "select t from Competition as t where t.place = :place")
})
public class Competition {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    private String place;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @OneToMany(mappedBy = "competition")
    private List<Participant> participants;

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Integer getSponsorCount() {
        HashSet<Sponsor> competitionSponsors = new HashSet<>();
        for (Participant participant : this.participants) {
            competitionSponsors.addAll(participant.getSponsors());
        }
        return competitionSponsors.size();
    }
}
