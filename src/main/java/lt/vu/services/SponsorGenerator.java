package lt.vu.services;

import lt.vu.entities.Sponsor;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class SponsorGenerator implements Serializable {

    public List<String> generateSponsors() {
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        List<String> sponsorNames = List.of("Nike", "Adidas", "Puma", "Reebok", "Under Armour", "New Balance", "Apple");
        HashSet<String> sponsorList = new HashSet<String>();
        for (int i = 0; i < 2; i++) {
            sponsorList.add(sponsorNames.get((int) (Math.random() * sponsorNames.size())));
        }
        return sponsorList.stream().toList();
    }
}