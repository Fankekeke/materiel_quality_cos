package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.DetectionTemplateInfo;
import cc.mrbird.febs.cos.service.IDetectionTemplateInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/detection-template-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DetectionTemplateInfoController {

    private final IDetectionTemplateInfoService detectionTemplateInfoService;

    /**
     * 分页获取检测模板信息
     *
     * @param page                  分页对象
     * @param detectionTemplateInfo 检测模板信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<DetectionTemplateInfo> page, DetectionTemplateInfo detectionTemplateInfo) {
        return R.ok(detectionTemplateInfoService.selectTemplatePage(page, detectionTemplateInfo));
    }

    /**
     * 根据类型获取物料
     *
     * @param type 类型（1.来料 2.过程 3.出货）
     * @return 结果
     */
    @GetMapping("/materiel")
    public R selectMaterielByTemplate(String type) {
        return R.ok(detectionTemplateInfoService.selectMaterielByTemplate(type));
    }

    /**
     * 检测模板信息详情
     *
     * @param id 检测模板ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(detectionTemplateInfoService.getById(id));
    }

    /**
     * 检测模板信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(detectionTemplateInfoService.list());
    }

    /**
     * 新增检测模板信息
     *
     * @param detectionTemplateInfo 检测模板信息
     * @return 结果
     */
    @PostMapping
    public R save(DetectionTemplateInfo detectionTemplateInfo) {
        detectionTemplateInfo.setCode("DET-" + System.currentTimeMillis());
        detectionTemplateInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(detectionTemplateInfoService.save(detectionTemplateInfo));
    }

    /**
     * 修改检测模板信息
     *
     * @param detectionTemplateInfo 检测模板信息
     * @return 结果
     */
    @PutMapping
    public R edit(DetectionTemplateInfo detectionTemplateInfo) {
        return R.ok(detectionTemplateInfoService.updateById(detectionTemplateInfo));
    }

    /**
     * 删除检测模板信息
     *
     * @param ids ids
     * @return 检测模板信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(detectionTemplateInfoService.removeByIds(ids));
    }
}
