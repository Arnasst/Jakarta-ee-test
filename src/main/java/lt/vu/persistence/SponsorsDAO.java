package lt.vu.persistence;

import lt.vu.entities.Participant;
import lt.vu.entities.Sponsor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SponsorsDAO {

    @Inject
    private EntityManager em;

    public void persist(Sponsor sponsor){
        this.em.persist(sponsor);
    }

    public List<Sponsor> findParticipantSponsors(Long participantId){
            return em.find(Participant.class, participantId).getSponsors();
    }

    public Sponsor update(Sponsor sponsor){
        return em.merge(sponsor);
    }

    public Sponsor findByName(String name){
        return em.createNamedQuery("Sponsor.findByName", Sponsor.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
