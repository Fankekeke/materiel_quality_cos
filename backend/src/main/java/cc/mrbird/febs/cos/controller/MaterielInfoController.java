package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.DetectionInfo;
import cc.mrbird.febs.cos.entity.MaterielInfo;
import cc.mrbird.febs.cos.service.IDetectionInfoService;
import cc.mrbird.febs.cos.service.IMaterielInfoService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/materiel-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaterielInfoController {

    private final IMaterielInfoService materielInfoService;

    private final IDetectionInfoService datasetInfoService;

    /**
     * 分页获取物料信息
     *
     * @param page         分页对象
     * @param materielInfo 物料信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<MaterielInfo> page, MaterielInfo materielInfo) {
        return R.ok(materielInfoService.selectMaterielPage(page, materielInfo));
    }

    /**
     * 根据物料ID获取检测项
     *
     * @param id 物料ID
     * @return 结果
     */
    @GetMapping("/checkItem")
    public R selectCheckItemById(Integer id) {
        MaterielInfo materielInfo = materielInfoService.getById(id);
        List<String> detectionIds = Arrays.asList(StrUtil.split(materielInfo.getDetectionIds(), ","));
        return R.ok(datasetInfoService.list(Wrappers.<DetectionInfo>lambdaQuery().in(DetectionInfo::getId, detectionIds)));
    }

    /**
     * 查询主页信息
     *
     * @return 结果
     */
    @GetMapping("/home/data")
    public R homeData() {
        return R.ok(materielInfoService.homeData());
    }

    /**
     * 查询看板数据
     *
     * @return 结果
     */
    @GetMapping("/selectBoard")
    public R selectBoard() {
        return R.ok(materielInfoService.selectBoard());
    }

    /**
     * 物料信息详情
     *
     * @param id 物料ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(materielInfoService.getById(id));
    }

    /**
     * 物料信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(materielInfoService.list());
    }

    /**
     * 新增物料信息
     *
     * @param materielInfo 物料信息
     * @return 结果
     */
    @PostMapping
    public R save(MaterielInfo materielInfo) {
        materielInfo.setCode("MA-" + System.currentTimeMillis());
        materielInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(materielInfoService.save(materielInfo));
    }

    /**
     * 修改物料信息
     *
     * @param materielInfo 物料信息
     * @return 结果
     */
    @PutMapping
    public R edit(MaterielInfo materielInfo) {
        return R.ok(materielInfoService.updateById(materielInfo));
    }

    /**
     * 删除物料信息
     *
     * @param ids ids
     * @return 物料信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(materielInfoService.removeByIds(ids));
    }
}
