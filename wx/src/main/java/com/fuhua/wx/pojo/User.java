package com.fuhua.wx.pojo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  TABLE null
 */
/**
* Created by Mybatis Generator on 2019/02/14
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private String userName;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String phone;

    /**
     * t_user
     */
    private static final long serialVersionUID = 1L;
}