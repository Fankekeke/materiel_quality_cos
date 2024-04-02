package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 物料信息
 *
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MaterielInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 物料编码
     */
    private String code;

    /**
     * 物料名称
     */
    private String name;

    /**
     * 规格型号
     */
    private String model;

    /**
     * 单位
     */
    private String unit;

    /**
     * 类型（0.物料 1.产品）
     */
    private String materielType;

    /**
     * 所属分类（1.物料产品分类 2.PVC展板 3.灯片）
     */
    private String materielSort;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 图片
     */
    private String images;


}
