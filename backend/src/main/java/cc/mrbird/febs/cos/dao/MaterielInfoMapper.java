package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.MaterielInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
public interface MaterielInfoMapper extends BaseMapper<MaterielInfo> {

    /**
     * 分页获取物料信息
     *
     * @param page         分页对象
     * @param materielInfo 物料信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectMaterielPage(Page<MaterielInfo> page, @Param("materielInfo") MaterielInfo materielInfo);

    /**
     * 本月检测数量/单
     *
     * @return 结果
     */
    int selectIncomeMonth();

    /**
     * 本月通过单数
     *
     * @return 结果
     */
    int selectWorkOrderMonth();

    /**
     * 本年检测数量/单
     *
     * @return 结果
     */
    int selectIncomeYear();

    /**
     * 本年通过单数
     *
     * @return 结果
     */
    int selectWorkOrderYear();

    /**
     * 查询十天内检测统计1
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectPaymentRecord1();

    /**
     * 查询十天内检测统计2
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectPaymentRecord2();

    /**
     * 查询十天内检测统计3
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectPaymentRecord3();

    /**
     * 查询十天内通过统计1
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectOrderRecord1();

    /**
     * 查询十天内通过统计2
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectOrderRecord2();

    /**
     * 查询十天内通过统计3
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectOrderRecord3();
}
