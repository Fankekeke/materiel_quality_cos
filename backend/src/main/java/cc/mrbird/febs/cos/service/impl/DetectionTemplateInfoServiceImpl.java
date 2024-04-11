package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.DetectionTemplateInfo;
import cc.mrbird.febs.cos.dao.DetectionTemplateInfoMapper;
import cc.mrbird.febs.cos.entity.MaterielInfo;
import cc.mrbird.febs.cos.service.IDetectionTemplateInfoService;
import cc.mrbird.febs.cos.service.IMaterielInfoService;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DetectionTemplateInfoServiceImpl extends ServiceImpl<DetectionTemplateInfoMapper, DetectionTemplateInfo> implements IDetectionTemplateInfoService {

    private final IMaterielInfoService materielInfoService;

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

    /**
     * 根据类型获取物料
     *
     * @param type 类型（1.来料 2.过程 3.出货）
     * @return 结果
     */
    @Override
    public List<MaterielInfo> selectMaterielByTemplate(String type) {
        List<DetectionTemplateInfo> templateInfoList = this.list();
        if (CollectionUtil.isEmpty(templateInfoList)) {
            return Collections.emptyList();
        }

        List<Integer> detectionIds = new ArrayList<>();
        for (DetectionTemplateInfo detectionTemplateInfo : templateInfoList) {
            if ("1".equals(type)) {
                if (detectionTemplateInfo.getCheckType().contains("来料检验")) {
                    detectionIds.add(detectionTemplateInfo.getId());
                }
            }
            if ("2".equals(type)) {
                if (detectionTemplateInfo.getCheckType().contains("首检") || detectionTemplateInfo.getCheckType().contains("末检") || detectionTemplateInfo.getCheckType().contains("巡检") || detectionTemplateInfo.getCheckType().contains("成品检验")) {
                    detectionIds.add(detectionTemplateInfo.getId());
                }
            }
            if ("3".equals(type)) {
                if (detectionTemplateInfo.getCheckType().contains("发货检验")) {
                    detectionIds.add(detectionTemplateInfo.getId());
                }
            }
        }
        if (CollectionUtil.isEmpty(detectionIds)) {
            return Collections.emptyList();
        }
        return materielInfoService.list(Wrappers.<MaterielInfo>lambdaQuery().in(MaterielInfo::getTemplateId, detectionIds));
    }
}
