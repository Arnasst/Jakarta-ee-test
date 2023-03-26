package lt.vu.persistence;

import lt.vu.entities.Participant;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ParticipantsDAO {

    @Inject
    private EntityManager em;

    public void persist(Participant participant){
        this.em.persist(participant);
    }

    public Participant findOne(Long id){
        return em.find(Participant.class, id);
    }

    public Participant update(Participant participant){
        return em.merge(participant);
    }
}
