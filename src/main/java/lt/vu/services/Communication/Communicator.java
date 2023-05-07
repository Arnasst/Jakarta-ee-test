package lt.vu.services.Communication;

import lt.vu.services.Animals.Animal;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class Communicator implements Serializable {
    @Inject
    private Greeter greeter;

    @Inject
    private Animal animal;

    public String greetVisitor() {
        return greeter.getGreeting();
    }

    public String emitSound() {
        return animal.emitSound();
    }

}
