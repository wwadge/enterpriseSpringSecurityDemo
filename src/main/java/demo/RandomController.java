package demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wwadge on 22/11/14.
 */

@RestController
public class RandomController {

    @RequestMapping("/foo") // also try /health /mappings etc
    public String thing() {
        return "Hello, World";
    }

}
