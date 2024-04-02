package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 工作站管理
 *
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 工作站编号
     */
    private String code;

    /**
     * 工作站名称
     */
    private String name;

    /**
     * 地点
     */
    private String address;

    /**
     * 所属车间
     */
    private String workshop;

    /**
     * 工序
     */
    private String process;

    /**
     * 创建时间
     */
    private String createDate;


}
