package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.MaterielInfo;
import cc.mrbird.febs.cos.dao.MaterielInfoMapper;
import cc.mrbird.febs.cos.service.IMaterielInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Service
public class MaterielInfoServiceImpl extends ServiceImpl<MaterielInfoMapper, MaterielInfo> implements IMaterielInfoService {

    /**
     * 分页获取物料信息
     *
     * @param page         分页对象
     * @param materielInfo 物料信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectMaterielPage(Page<MaterielInfo> page, MaterielInfo materielInfo) {
        return baseMapper.selectMaterielPage(page, materielInfo);
    }
}
