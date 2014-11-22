package demo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
    private static AuthenticationManager am = new SampleAuthenticationManager();

    public static void main(String[] args) throws IOException {


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

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

        System.out.println(username);

    }


    }
