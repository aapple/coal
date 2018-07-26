package com.young.coal.business.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "user")
public class User {

    private String userId;
    private String userName;
    private String password;
    private String role;
    private Date createDate;

}
