package org.automation.automate.api.pojo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDetailsResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;
    private String updatedAt;
}
