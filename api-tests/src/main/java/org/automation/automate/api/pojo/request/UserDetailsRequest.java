package org.automation.automate.api.pojo.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsRequest {
    private String name;
    private String job;
}
