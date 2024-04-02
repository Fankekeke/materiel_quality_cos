package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.IncomeCheckInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
public interface IncomeCheckInfoMapper extends BaseMapper<IncomeCheckInfo> {

    /**
     * 分页获取来料检验信息
     *
     * @param page            分页对象
     * @param incomeCheckInfo 来料检验信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectIncomeCheckPage(Page<IncomeCheckInfo> page, @Param("incomeCheckInfo") IncomeCheckInfo incomeCheckInfo);
}
