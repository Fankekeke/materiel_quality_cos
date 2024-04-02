package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 工单管理
 *
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 工单编码
     */
    private String code;

    /**
     * 工单名称
     */
    private String name;

    /**
     * 工单来源
     */
    private String orderSource;

    /**
     * 物料ID
     */
    private Integer materielId;

    /**
     * 工单数量
     */
    private Integer orderNum;

    /**
     * 客户ID
     */
    private Integer clienteleId;

    /**
     * 创建时间
     */
    private String createDate;

    @TableField(exist = false)
    private String materielName;

    @TableField(exist = false)
    private String clienteleName;


}
