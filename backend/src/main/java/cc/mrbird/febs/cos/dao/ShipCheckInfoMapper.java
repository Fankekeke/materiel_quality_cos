package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ShipCheckInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
public interface ShipCheckInfoMapper extends BaseMapper<ShipCheckInfo> {

    /**
     * 分页获取出货检验信息
     *
     * @param page          分页对象
     * @param shipCheckInfo 出货检验信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectShipCheckPage(Page<ShipCheckInfo> page, @Param("shipCheckInfo") ShipCheckInfo shipCheckInfo);
}
