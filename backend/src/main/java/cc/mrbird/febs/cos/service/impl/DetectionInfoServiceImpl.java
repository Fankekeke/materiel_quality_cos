package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.DetectionInfo;
import cc.mrbird.febs.cos.dao.DetectionInfoMapper;
import cc.mrbird.febs.cos.service.IDetectionInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Service
public class DetectionInfoServiceImpl extends ServiceImpl<DetectionInfoMapper, DetectionInfo> implements IDetectionInfoService {

    /**
     * 分页获取检测项信息
     *
     * @param page          分页对象
     * @param detectionInfo 检测项信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectDetectionPage(Page<DetectionInfo> page, DetectionInfo detectionInfo) {
        return baseMapper.selectDetectionPage(page, detectionInfo);
    }
}
