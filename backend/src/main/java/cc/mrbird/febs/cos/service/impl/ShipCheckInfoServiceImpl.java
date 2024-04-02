package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ShipCheckInfo;
import cc.mrbird.febs.cos.dao.ShipCheckInfoMapper;
import cc.mrbird.febs.cos.service.IShipCheckInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Service
public class ShipCheckInfoServiceImpl extends ServiceImpl<ShipCheckInfoMapper, ShipCheckInfo> implements IShipCheckInfoService {

    /**
     * 分页获取出货检验信息
     *
     * @param page          分页对象
     * @param shipCheckInfo 出货检验信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectShipCheckPage(Page<ShipCheckInfo> page, ShipCheckInfo shipCheckInfo) {
        return baseMapper.selectShipCheckPage(page, shipCheckInfo);
    }
}
