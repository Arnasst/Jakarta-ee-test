package lt.vu.services.Animals;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

public interface Animal {
    String emitSound();
}
