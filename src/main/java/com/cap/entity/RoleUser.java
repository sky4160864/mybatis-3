package com.cap.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author C.H
 * @date 2021/6/16 16:17
 */

@Data
@Builder
@Accessors(chain = true)
public class RoleUser {
    private Integer id;

    private Integer roleId;

    private Integer userId;

}
