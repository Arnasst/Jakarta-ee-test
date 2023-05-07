package lt.vu.services.Animals;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Named;
import java.io.Serializable;

//@Default
//@Model
@Named
@RequestScoped
public class Dog implements Animal, Serializable {
    public String emitSound() {
        return "Woof " + toString();
    }
}
