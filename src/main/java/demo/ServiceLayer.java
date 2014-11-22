package demo;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

/**
 * Created by wwadge on 22/11/14.
 */
@Component
public class ServiceLayer {

    @Secured("ROLE_STAFF")
    public void findAccounts(){
        System.out.println("I'm in findAccounts");
    }

    @Secured("ROLE_STAFF")
    public void post(){
        System.out.println("I'm in post");
    }
}
