package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.DetectionTemplateInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
public interface DetectionTemplateInfoMapper extends BaseMapper<DetectionTemplateInfo> {

    /**
     * 分页获取检测模板信息
     *
     * @param page                  分页对象
     * @param detectionTemplateInfo 检测模板信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectTemplatePage(Page<DetectionTemplateInfo> page, @Param("detectionTemplateInfo") DetectionTemplateInfo detectionTemplateInfo);
}
