package com.cap.dao;

import java.util.List;

import com.cap.entity.Role;
import com.cap.entity.RoleWithUsers;
import org.apache.ibatis.annotations.Param;

public interface RoleDao {
    /**
     * 获取角色
     *
     * @param id 主键
     * @return /
     */
    Role getRole(@Param("id") Long id);

    RoleWithUsers getRoleWithUsers(@Param("id") Long id);

    List<Role> getRoleWithUsers2(@Param("id") Long id);

    List<Role> selectByIdAndRoleName(@Param("id") Long id, @Param("roleName") String roleName);


    /**
     * 获取角色
     *
     * @param roleName 角色名称
     * @return /
     */
    Role findRole(String roleName);

    int deleteRole(Long id);

    int insertRole(Role role);

    int updateById(@Param("updated")Role updated,@Param("id")Long id);


}
