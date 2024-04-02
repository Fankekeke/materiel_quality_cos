package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.StationInfo;
import cc.mrbird.febs.cos.dao.StationInfoMapper;
import cc.mrbird.febs.cos.service.IStationInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Service
public class StationInfoServiceImpl extends ServiceImpl<StationInfoMapper, StationInfo> implements IStationInfoService {

    /**
     * 分页获取工作站信息
     *
     * @param page        分页对象
     * @param stationInfo 工作站信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectStationPage(Page<StationInfo> page, StationInfo stationInfo) {
        return baseMapper.selectStationPage(page, stationInfo);
    }
}
