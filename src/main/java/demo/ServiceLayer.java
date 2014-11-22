package demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 * Created by wwadge on 22/11/14.
 */
@Component
public class ServiceLayer {

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void findAccounts(){
        System.out.println("I'm in findAccounts");
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    public void post(){
        System.out.println("I'm in post");
    }
}
