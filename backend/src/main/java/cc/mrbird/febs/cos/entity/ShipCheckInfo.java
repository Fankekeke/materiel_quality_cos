package cc.mrbird.febs.cos.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 出货检验
 *
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ShipCheckInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 检验单编号
     */
    private String code;

    /**
     * 检验单名称
     */
    private String name;

    /**
     * 物料ID
     */
    private Integer materielId;

    /**
     * 物料名称
     */
    private String materielName;

    /**
     * 单位
     */
    private String unit;

    /**
     * 规格型号
     */
    private String model;

    /**
     * 客户ID
     */
    private Integer clienteleId;

    /**
     * 客户名称
     */
    private String clienteleName;

    /**
     * 批次号
     */
    private String batchNumber;

    /**
     * 发货数量
     */
    private Integer shipNum;

    /**
     * 出货日期
     */
    private String shipDate;

    /**
     * 检测数量
     */
    private Integer checkNum;

    /**
     * 检测日期
     */
    private String checkDate;

    /**
     * 检测结果（0.等待检测 1.通过 2.不通过）
     */
    private String checkStatus;

    /**
     * 单据状态（0.已创建 1.检测中 2.已完成）
     */
    private String receiptStatus;

    /**
     * 备注
     */
    private String content;

    /**
     * 致命缺陷率
     */
    private BigDecimal fatalDefectRate;

    /**
     * 严重缺陷率
     */
    private BigDecimal criticalDefectRate;

    /**
     * 轻微缺陷率
     */
    private BigDecimal minorDefectRate;

    /**
     * 致命缺陷数量
     */
    private Integer fatalDefectNum;

    /**
     * 严重缺数量
     */
    private Integer criticalDefectNum;

    /**
     * 轻微缺数量
     */
    private Integer minorDefectNum;

    private Integer unqualifiedNum;

    private Integer staffId;

    @TableField(exist = false)
    private String detectionCheck;

}
