package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.StationInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
public interface IStationInfoService extends IService<StationInfo> {

    /**
     * 分页获取工作站信息
     *
     * @param page        分页对象
     * @param stationInfo 工作站信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectStationPage(Page<StationInfo> page, StationInfo stationInfo);
}
