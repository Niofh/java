package resources.mapper;

import java.com.mmall.pojo.User;

/**
* 
*/
public interface UserMapper {
    /**
     *  deleteByPrimaryKey
     */
    int deleteByPrimaryKey(Long id);

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
    User selectByPrimaryKey(Long id);

    /**
     *  updateByPrimaryKeySelective
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *  updateByPrimaryKeyWithBLOBs
     */
    int updateByPrimaryKeyWithBLOBs(User record);

    /**
     *  updateByPrimaryKey
     */
    int updateByPrimaryKey(User record);
}