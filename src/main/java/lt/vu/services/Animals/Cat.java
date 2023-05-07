package lt.vu.services.Animals;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;

@Specializes
@Alternative
public class Cat extends Dog {
    @Override
    public String emitSound() {
        return "Meow " + toString();
    }
}
