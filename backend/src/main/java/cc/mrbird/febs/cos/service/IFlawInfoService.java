package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.FlawInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
public interface IFlawInfoService extends IService<FlawInfo> {

    /**
     * 分页获取常见缺陷信息
     *
     * @param page     分页对象
     * @param flawInfo 常见缺陷信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectFlawPage(Page<FlawInfo> page, FlawInfo flawInfo);
}
