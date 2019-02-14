package com.mmall.pojo;

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
    private Long id;

    /**
     * 年齡
     */
    private Byte age;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String pwd;

    /**
     * 
     */
    private String description;

    /**
     * user
     */
    private static final long serialVersionUID = 1L;
}