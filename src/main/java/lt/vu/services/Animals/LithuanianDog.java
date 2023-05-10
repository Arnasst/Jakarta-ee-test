package lt.vu.services.Animals;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Specializes;

@Specializes
@RequestScoped
public class LithuanianDog extends Dog {
    @Override
    public String emitSound() {
        return "Au " + toString();
    }
}
