package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.SupplierInfo;
import cc.mrbird.febs.cos.dao.SupplierInfoMapper;
import cc.mrbird.febs.cos.service.ISupplierInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Service
public class SupplierInfoServiceImpl extends ServiceImpl<SupplierInfoMapper, SupplierInfo> implements ISupplierInfoService {

    /**
     * 分页获取供应商信息
     *
     * @param page         分页对象
     * @param supplierInfo 供应商信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectSupplierPage(Page<SupplierInfo> page, SupplierInfo supplierInfo) {
        return baseMapper.selectSupplierPage(page, supplierInfo);
    }
}
