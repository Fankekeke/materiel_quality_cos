package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.MaterielInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
public interface MaterielInfoMapper extends BaseMapper<MaterielInfo> {

    /**
     * 分页获取物料信息
     *
     * @param page         分页对象
     * @param materielInfo 物料信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectMaterielPage(Page<MaterielInfo> page, @Param("materielInfo") MaterielInfo materielInfo);
}
