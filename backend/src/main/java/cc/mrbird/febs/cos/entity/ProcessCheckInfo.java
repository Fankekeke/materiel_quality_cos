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
 * 过程检验
 *
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProcessCheckInfo implements Serializable {

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
     * 检验类型（1.首检 2.末检 3.自检 4.巡检 5.点检 6.终检）
     */
    private String checkType;

    /**
     * 工单ID
     */
    private Integer orderId;

    /**
     * 工单名称
     */
    private String orderName;

    /**
     * 工作站ID
     */
    private Integer stationId;

    /**
     * 工作站名称
     */
    private String stationName;

    /**
     * 检测数量
     */
    private Integer checkNum;

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
     * 工序编号
     */
    private String processCode;

    /**
     * 工序名称
     */
    private String processName;

    /**
     * 任务编号
     */
    private String taskCode;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 不合格数量
     */
    private Integer unqualifiedNum;

    /**
     * 合格品数量
     */
    private Integer qualifiedNum;

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

    /**
     * 检测日期
     */
    private String checkDate;

    /**
     * 检测数量
     */
    private Integer checkQuantity;

    /**
     * 检测人员
     */
    private Integer staffId;

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

}
