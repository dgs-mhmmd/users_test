package org.users.test.request;

import lombok.Data;


@Data
public class UserRequest {
    public String firstName;
    public String lastName;
    public String UserName;
    public String password;

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() < 2 || firstName.length()>50) {
            throw new IllegalArgumentException("firstName must be at least 2 characters long");
        }
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() < 2 || lastName.length()>50) {
            throw new IllegalArgumentException("lastname must be at least 2 characters long");
        }
        this.lastName = lastName;
    }
    public void setUserName(String UserName) {
        if (UserName == null || UserName.length() < 2 || UserName.length()>50) {
            throw new IllegalArgumentException("username must be at least 2 characters long");
        }
        this.UserName = UserName;
    }

}

