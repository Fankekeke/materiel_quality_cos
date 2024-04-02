package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.IncomeCheckInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
public interface IIncomeCheckInfoService extends IService<IncomeCheckInfo> {

    /**
     * 分页获取来料检验信息
     *
     * @param page            分页对象
     * @param incomeCheckInfo 来料检验信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectIncomeCheckPage(Page<IncomeCheckInfo> page, IncomeCheckInfo incomeCheckInfo);
}
