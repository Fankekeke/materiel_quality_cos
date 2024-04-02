package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.FlawInfo;
import cc.mrbird.febs.cos.dao.FlawInfoMapper;
import cc.mrbird.febs.cos.service.IFlawInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Service
public class FlawInfoServiceImpl extends ServiceImpl<FlawInfoMapper, FlawInfo> implements IFlawInfoService {

    /**
     * 分页获取常见缺陷信息
     *
     * @param page     分页对象
     * @param flawInfo 常见缺陷信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectFlawPage(Page<FlawInfo> page, FlawInfo flawInfo) {
        return baseMapper.selectFlawPage(page, flawInfo);
    }
}
