package com.techmango.dao;

import javax.transaction.Transactional;

import com.techmango.models.Grant;

@Transactional
public interface GrantDao extends BaseDao<Grant> {

}
