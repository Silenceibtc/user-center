package cn.edu.dlmu.back.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别 0 - 女 1 - 男
     */
    private Integer gender;

    /**
     * 头像url

     */
    private String avatarUrl;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态 0 - 正常
     */
    private Integer userStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 0 - 未删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 用户身份 0 - 管理员 1 - 普通用户
     */
    private Integer identity;

    /**
     * 标签列表
     */
    private String tags;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}