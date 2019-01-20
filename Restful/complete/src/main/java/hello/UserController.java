package hello;

import org.springframework.web.bind.annotation.*;

import java.util.List;


import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Fred on 2019-01-19.
 */
@RestController
public class UserController {

    private final AtomicLong counter = new AtomicLong(2);
    private final UserService service = new UserService();

    @GetMapping("/users")
    public List<User> getAllUsers() {
    	return service.getAll();
    }

    @GetMapping("/pickups")
    public List<User> getPickups(@RequestParam(value = "stock", defaultValue = "6") int stock) {
        return service.getAllWhere(stock);
    }

    @PostMapping("/users")
    public String addUser(@RequestBody User user) {
        service.add(user);
        return "200 OK but may not be ok, we do not know. lul";
    }
    
    @GetMapping("/user")
    public User findUserByName(@RequestParam(value = "name", defaultValue = "") String name) {
        if(name.equals("")) {
            // return "Missing name parameter";
            return null;
        } else {
            return service.get(name);
        }
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/user/{id}")
    public String updateUser(@RequestBody User user, @PathVariable Long id) {
        user.setId(id); // This is sketchy.
        service.update(user);
        return "200 OK but may not be ok, we do not know. lul";
    }
}
