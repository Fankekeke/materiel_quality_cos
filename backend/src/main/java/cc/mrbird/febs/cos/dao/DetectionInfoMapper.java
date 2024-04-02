package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.DetectionInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
public interface DetectionInfoMapper extends BaseMapper<DetectionInfo> {

    /**
     * 分页获取检测项信息
     *
     * @param page          分页对象
     * @param detectionInfo 检测项信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectDetectionPage(Page<DetectionInfo> page, @Param("detectionInfo") DetectionInfo detectionInfo);
}
