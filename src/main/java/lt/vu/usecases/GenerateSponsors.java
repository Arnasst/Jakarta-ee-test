package lt.vu.usecases;

import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.SponsorGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateSponsors implements Serializable {
    @Inject
    SponsorGenerator sponsorGenerator;

    private CompletableFuture<List<String>> sponsorGeneratorTask = null;

    @LoggedInvocation
    public void generateNewSponsors() {
        sponsorGeneratorTask = CompletableFuture.supplyAsync(() -> sponsorGenerator.generateSponsors());
    }

    public boolean isSponsorGenerationRunning() {
        return sponsorGeneratorTask != null && !sponsorGeneratorTask.isDone();
    }

    public String getSponsorGenerationStatus() throws ExecutionException, InterruptedException {
        if (sponsorGeneratorTask == null) {
            return null;
        } else if (isSponsorGenerationRunning()) {
            return "Sponsor generation is in progress";
        }
        return "Suggested sponsors: " + sponsorGeneratorTask.get();
    }
}
