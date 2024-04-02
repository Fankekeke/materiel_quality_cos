package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.IncomeCheckInfo;
import cc.mrbird.febs.cos.dao.IncomeCheckInfoMapper;
import cc.mrbird.febs.cos.service.IIncomeCheckInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Service
public class IncomeCheckInfoServiceImpl extends ServiceImpl<IncomeCheckInfoMapper, IncomeCheckInfo> implements IIncomeCheckInfoService {

    /**
     * 分页获取来料检验信息
     *
     * @param page            分页对象
     * @param incomeCheckInfo 来料检验信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectIncomeCheckPage(Page<IncomeCheckInfo> page, IncomeCheckInfo incomeCheckInfo) {
        return baseMapper.selectIncomeCheckPage(page, incomeCheckInfo);
    }
}
