package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.DetectionTemplateInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
public interface IDetectionTemplateInfoService extends IService<DetectionTemplateInfo> {

    /**
     * 分页获取检测模板信息
     *
     * @param page                  分页对象
     * @param detectionTemplateInfo 检测模板信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectTemplatePage(Page<DetectionTemplateInfo> page, DetectionTemplateInfo detectionTemplateInfo);
}
