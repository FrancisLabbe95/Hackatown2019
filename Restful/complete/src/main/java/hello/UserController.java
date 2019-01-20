package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Fred on 2019-01-19.
 */
@RestController
public class UserController {

    private final AtomicLong counter = new AtomicLong();
    private final UserService service = new UserService();

    @RequestMapping("/users")
    public List<User> users() {
    	return service.getAll();
    }
    
    @RequestMapping("/user")
    public String user(@RequestParam(value = "name", defaultValue = "Bob") String name,@RequestParam(value = "type") int type ) {
        service.add(new User(counter.incrementAndGet(), name, type));
        return "200 OK";
    }
    
    @RequestMapping("/user/a")
    public String a() {
        return "allo";
    }
}
