package com.techmango.dao;

import javax.transaction.Transactional;

import com.techmango.models.Role;

@Transactional
public interface RoleDao extends BaseDao<Role> {

}
