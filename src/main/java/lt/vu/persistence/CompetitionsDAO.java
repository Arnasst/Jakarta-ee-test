package lt.vu.persistence;

import lt.vu.entities.Competition;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
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

    public Competition findOne(Long id) {
        return em.find(Competition.class, id);
    }
}
