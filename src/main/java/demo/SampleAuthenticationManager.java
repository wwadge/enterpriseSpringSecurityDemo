package demo;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created by wwadge on 22/11/14.
 */
public class SampleAuthenticationManager implements AuthenticationProvider {
    // the default implementation of Spring is in ProviderManager class, that simply goes through each
    // registered authentication provider until it gets a response.

    static final List<GrantedAuthority> AUTHORITIES = ImmutableList.of(new SimpleGrantedAuthority("ROLE_USER"));

    @Autowired
    UserDetailsService userDetailsService;

    public Authentication authenticate(Authentication auth) throws AuthenticationException {


        UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getName());

        if (userDetails.getPassword().equals(auth.getCredentials())) {
            return new UsernamePasswordAuthenticationToken(auth.getName(),
                    auth.getCredentials(), AUTHORITIES);
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
