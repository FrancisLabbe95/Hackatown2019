package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Fred on 2019-01-19.
 */
@RestController
public class UserController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/user")
    public User user(@RequestParam(value = "name", defaultValue = "Bob") String name,@RequestParam(value = "type") int type ) {
        return new User(counter.incrementAndGet(), name, UserType.fromInt(type));
    }
    @RequestMapping("/user/a")
    public String a() {
        return "allo";
    }
}
