package lt.vu.services.Communication;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Alternative
@Named
@SessionScoped
public class FormalGreeter implements Greeter, Serializable {
    public String getGreeting() {
        String greetingStart = "";
        Date date = new Date();
        if (date.getHours() < 12) {
            greetingStart = "Good morning";
        } else if (date.getHours() < 18) {
            greetingStart = "Good afternoon";
        } else {
            greetingStart = "Good evening";
        }

        return greetingStart + " | " + new Date() + " | " + toString();
    }
}
