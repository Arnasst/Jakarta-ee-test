package lt.vu.services;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@SessionScoped
public class Greeter implements Serializable {

    public String greetVisitor() {
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
