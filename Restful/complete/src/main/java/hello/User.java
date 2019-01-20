package hello;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Created by Fred on 2019-01-19.
 */
@Entity
@Table( name = "Users" )
public class User {
    private long id;
    private String name;
   /* private final String email;
    private final Date availability;
    private final String address;
    private final int stock; */
    private int userType;
    
    
    public User() {
    }

    public User(long id, String name, int userType) {
        this.id = id;
        this.name = name;
        this.userType = userType;
    }
    
    @Id
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public int getUserType() {
        return userType;
    }
    
    public void setUserType(int userType) {
        this.userType = userType;
    }
}
