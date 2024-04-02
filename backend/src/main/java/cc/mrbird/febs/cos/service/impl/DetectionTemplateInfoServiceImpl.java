package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.DetectionTemplateInfo;
import cc.mrbird.febs.cos.dao.DetectionTemplateInfoMapper;
import cc.mrbird.febs.cos.service.IDetectionTemplateInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Service
public class DetectionTemplateInfoServiceImpl extends ServiceImpl<DetectionTemplateInfoMapper, DetectionTemplateInfo> implements IDetectionTemplateInfoService {

    /**
     * 分页获取检测模板信息
     *
     * @param page                  分页对象
     * @param detectionTemplateInfo 检测模板信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectTemplatePage(Page<DetectionTemplateInfo> page, DetectionTemplateInfo detectionTemplateInfo) {
        return baseMapper.selectTemplatePage(page, detectionTemplateInfo);
    }
}
