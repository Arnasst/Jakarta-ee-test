package lt.vu.rest;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Competition;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CompetitionsDAO;
import lt.vu.rest.contracts.CompetitionDto;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.RollbackException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.lang.Thread.sleep;

//@ApplicationScoped
@RequestScoped
@Path("/competitions")
@LoggedInvocation
public class CompetitionController {

    @Inject
    @Setter @Getter
    private CompetitionsDAO competitionsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Competition competition = competitionsDAO.findOne(id);
        if (competition == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CompetitionDto competitionDto = new CompetitionDto();
        competitionDto.setPlace(competition.getPlace());

        return Response.ok(competitionDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Long compId,
            CompetitionDto competitionData) {
        try {
            return updateCompetition(compId, competitionData);
        }
        // javax.transaction.RollbackException
        catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
//        catch (RollbackException e) {
//            if (e.getCause() instanceof OptimisticLockException) {
//                return Response.status(Response.Status.FORBIDDEN).build();
//            }
//        }
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    private Response updateCompetition(final Long compId, CompetitionDto competitionData)
    {
//        Competition existingCompetition = competitionsDAO.findOne(compId);
            Competition existingCompetition = competitionsDAO.findOneLocked(compId);
        if (existingCompetition == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingCompetition.setPlace(competitionData.getPlace());
        try{
            sleep(3000);
        } catch (InterruptedException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        competitionsDAO.update(existingCompetition);
        return Response.ok().build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Long create(
            CompetitionDto competitionData) {
        Competition newCompetition = new Competition();
        newCompetition.setPlace(competitionData.getPlace());
        competitionsDAO.persist(newCompetition);

        return newCompetition.getId();
    }


}
