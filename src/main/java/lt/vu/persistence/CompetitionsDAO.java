package lt.vu.persistence;

import lt.vu.entities.Competition;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.List;

//@ApplicationScoped
@RequestScoped
public class CompetitionsDAO {

    @Inject
    private EntityManager em;

    public List<Competition> loadAll() {
        return em.createNamedQuery("Competition.findAll", Competition.class).getResultList();
    }

    public Competition loadOne(String place) {
        //      This is SQL injection resistant.
        return em.createNamedQuery("Competition.findOne", Competition.class).setParameter("place", place).getSingleResult();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Competition competition){
        this.em.persist(competition);
    }

    public Competition update(Competition competition){
        return em.merge(competition);
    }

    public void flush() {
        em.flush();
    }

    public Competition findOne(Long id) {
        return em.find(Competition.class, id);
    }

    public Competition findOneLocked(Long id)
    {
        return em.find(Competition.class, id,
                LockModeType.OPTIMISTIC_FORCE_INCREMENT);
    }
}
