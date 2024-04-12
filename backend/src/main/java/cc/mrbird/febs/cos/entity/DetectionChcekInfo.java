package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 检测项填报
 *
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DetectionChcekInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 检测项类型
     */
    private String typeName;

    /**
     * 检测工具
     */
    private String checkUtil;

    /**
     * 检测要求
     */
    private String checkRequire;

    /**
     * 标准值
     */
    private String standardValue;

    /**
     * 单位
     */
    private String unit;

    /**
     * 误差上限
     */
    private BigDecimal errorMax;

    /**
     * 误差下限
     */
    private BigDecimal errorMin;

    /**
     * 备注
     */
    private String content;

    /**
     * 所属编号
     */
    private String code;


}
