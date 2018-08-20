package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Welcome to the start page!";
    }

    @RequestMapping("/second")
    public String index2() {
        return "Second page!";
    }

}