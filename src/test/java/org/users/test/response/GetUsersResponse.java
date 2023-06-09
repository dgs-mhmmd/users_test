package org.users.test.response;

import lombok.Data;

@Data
public class GetUsersResponse {
    public String id;
    public String username;
    public String firstName;
    public String lastName;
    public boolean isActive;
}

