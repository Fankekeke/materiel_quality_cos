package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ClienteleInfo;
import cc.mrbird.febs.cos.dao.ClienteleInfoMapper;
import cc.mrbird.febs.cos.service.IClienteleInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Service
public class ClienteleInfoServiceImpl extends ServiceImpl<ClienteleInfoMapper, ClienteleInfo> implements IClienteleInfoService {

    /**
     * 分页获取客户信息
     *
     * @param page          分页对象
     * @param clienteleInfo 客户信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectClientelePage(Page<ClienteleInfo> page, ClienteleInfo clienteleInfo) {
        return baseMapper.selectClientelePage(page, clienteleInfo);
    }
}
