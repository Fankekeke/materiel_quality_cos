package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ProcessCheckInfo;
import cc.mrbird.febs.cos.dao.ProcessCheckInfoMapper;
import cc.mrbird.febs.cos.service.IProcessCheckInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Service
public class ProcessCheckInfoServiceImpl extends ServiceImpl<ProcessCheckInfoMapper, ProcessCheckInfo> implements IProcessCheckInfoService {

    /**
     * 分页获取过程检验信息
     *
     * @param page             分页对象
     * @param processCheckInfo 过程检验信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectProcessPage(Page<ProcessCheckInfo> page, ProcessCheckInfo processCheckInfo) {
        return baseMapper.selectProcessPage(page, processCheckInfo);
    }
}
