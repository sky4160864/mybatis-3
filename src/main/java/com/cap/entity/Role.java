package com.cap.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * (Role)实体类
 *
 * @author Captain H
 * @since 2020-06-19 15:53:40
 */
@Data
@NoArgsConstructor
public class Role {

    private long id;
    private String roleName;
    private String note;
    private List<User> users;
}
