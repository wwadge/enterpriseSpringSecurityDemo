package demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wwadge on 22/11/14.
 */

@RestController
public class RandomController extends WebMvcConfigurerAdapter {

    @RequestMapping("/foo") // also try /health /mappings etc
    public String thing() {
        return "Hello, World";
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

}
