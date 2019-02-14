package com.fuhua.wx.dao;

import com.fuhua.wx.pojo.User;

/**
* 
*/
public interface UserMapper {
    /**
     *  deleteByPrimaryKey
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     *  insert
     */
    int insert(User record);

    /**
     *  insertSelective
     */
    int insertSelective(User record);

    /**
     *  selectByPrimaryKey
     */
    User selectByPrimaryKey(Integer userId);

    /**
     *  updateByPrimaryKeySelective
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *  updateByPrimaryKey
     */
    int updateByPrimaryKey(User record);
}