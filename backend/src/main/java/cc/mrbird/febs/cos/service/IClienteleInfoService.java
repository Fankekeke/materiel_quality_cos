package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ClienteleInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
public interface IClienteleInfoService extends IService<ClienteleInfo> {

    /**
     * 分页获取客户信息
     *
     * @param page          分页对象
     * @param clienteleInfo 客户信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectClientelePage(Page<ClienteleInfo> page, ClienteleInfo clienteleInfo);
}
