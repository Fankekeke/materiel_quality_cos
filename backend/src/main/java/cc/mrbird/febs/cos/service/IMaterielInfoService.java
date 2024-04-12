package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.MaterielInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
public interface IMaterielInfoService extends IService<MaterielInfo> {

    /**
     * 分页获取物料信息
     *
     * @param page         分页对象
     * @param materielInfo 物料信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectMaterielPage(Page<MaterielInfo> page, MaterielInfo materielInfo);

    /**
     * 查询主页信息
     *
     * @return 结果
     */
    LinkedHashMap<String, Object> homeData();

    /**
     * 查询看板数据
     *
     * @return 结果
     */
    LinkedHashMap<String, Object> selectBoard();
}
