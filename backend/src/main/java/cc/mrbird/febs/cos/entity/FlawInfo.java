package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 常见缺陷
 *
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FlawInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    private String code;

    /**
     * 缺陷名称
     */
    private String name;

    /**
     * 缺陷描述
     */
    private String content;

    /**
     * 检测项类型（1.尺寸 2.外观 3.重量 4.性能 5.成分）
     */
    private String detectionItem;

    /**
     * 缺陷等级（1.致命缺陷 2.严重缺陷 3.轻微缺陷）
     */
    private String flawLevel;

    /**
     * 创建时间
     */
    private String createDate;


}
