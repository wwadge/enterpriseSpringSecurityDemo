package demo;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by wwadge on 22/11/14.
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        throw new AccessDeniedException("Access is denied");

    }

    public boolean supports(ConfigAttribute attribute) {
        return attribute.getAttribute() != null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }

}

