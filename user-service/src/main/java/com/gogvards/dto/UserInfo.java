package com.gogvards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String id;
    private String lastName;
    private String middleName;
    private String firstName;
    private String userRole;
    private String classTitle;
}
