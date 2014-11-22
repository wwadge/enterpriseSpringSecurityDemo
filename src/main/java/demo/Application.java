package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Configuration
@ComponentScan
// defined in xml @EnableGlobalMethodSecurity(prePostEnabled = true)
@ImportResource("security.xml")
@EnableAutoConfiguration
public class Application {

    @Autowired
    AuthenticationManager am;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ServiceLayer serviceLayer;


    public void run() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(passwordEncoder.encode("wadge"));

        while(true) {
            System.out.println("Please enter your username:");
            String name = in.readLine();
            System.out.println("Please enter your password:");
            String password = in.readLine();
            try {
                Authentication request = new UsernamePasswordAuthenticationToken(name, password);

                Authentication result = am.authenticate(request);
                SecurityContextHolder.getContext().setAuthentication(result);
                break;
            } catch(AuthenticationException e) {
                System.out.println("Authentication failed: " + e.getMessage());
            }
        }
        System.out.println("Successfully authenticated. Security context contains: " +
                SecurityContextHolder.getContext().getAuthentication());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        // most auth providers return UserDetails as a principal
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        serviceLayer.findAccounts();
        serviceLayer.post();

        System.out.println(username);

    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args).getBean("application", Application.class).run();
    }


    }
