package cn.edu.dlmu.back.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 标签表
 * @TableName tag
 */
@TableName(value ="tag")
@Data
public class Tag implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标签名
     */
    @TableField(value = "tagName")
    private String tagname;

    /**
     * 用户 id
     */
    @TableField(value = "userId")
    private Long userid;

    /**
     * 父标签 id
     */
    @TableField(value = "parentId")
    private Long parentid;

    /**
     * 是否为父标签 0 - 否 1 - 是
     */
    @TableField(value = "isParent")
    private Integer isparent;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createtime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private Date updatetime;

    /**
     * 是否删除 0 - 未删除 1 - 已删除
     */
    @TableField(value = "isDelete")
    private Integer isdelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}