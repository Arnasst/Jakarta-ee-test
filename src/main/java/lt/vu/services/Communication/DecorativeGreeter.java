package lt.vu.services.Communication;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Date;

@Decorator
public abstract class DecorativeGreeter implements Greeter, Serializable {

    @Inject
    @Delegate
    @Any
    @Default
    Greeter greeter;

    private int greetCounter = 0;
    public String getGreeting() {
        greetCounter++;
        if (greetCounter == 3) {
            greetCounter = 0;
            return "For the third time, hello... " + new Date() + " | " + toString();
        }
        return greeter.getGreeting();
    }
}
