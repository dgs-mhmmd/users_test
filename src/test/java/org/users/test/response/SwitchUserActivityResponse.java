package org.users.test.response;

import lombok.Data;

@Data
public class SwitchUserActivityResponse {
    public String userId;
    public boolean isActive;
}
