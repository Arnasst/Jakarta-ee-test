package lt.vu.services.Communication;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Alternative
@Named
@SessionScoped
public class FriendlyGreeter implements Greeter, Serializable {
    public String getGreeting() {
        return "Hello, " + new Date() + " | " + toString();
    }
}
