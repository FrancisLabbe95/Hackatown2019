package hello;

/**
 * Created by Fred on 2019-01-19.
 */
public class User {
    private final long id;
    private final String name;
    private final UserType userType;


    public User(long id, String name, UserType userType) {
        this.id = id;
        this.name = name;
        this.userType = userType;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserType getUserType() {
        return userType;
    }
}
