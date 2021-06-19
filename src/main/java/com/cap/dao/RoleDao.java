package com.cap.dao;

import com.cap.entity.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleDao {
    /**
     * 获取角色
     * @param id 主键
     * @return /
     */
    public Role getRole(@Param("id") Long id);

    public Role getRoleWithUsers(@Param("id") Long id);

    public Role getRoleWithUsers2(@Param("id") Long id);

    /**
     * 获取角色
     * @param roleName 角色名称
     * @return /
     */
    public Role findRole(String roleName);

    public int deleteRole(Long id);

    public int insertRole(Role role);
}
