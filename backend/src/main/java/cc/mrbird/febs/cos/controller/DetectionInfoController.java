package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.DetectionInfo;
import cc.mrbird.febs.cos.service.IDetectionInfoService;
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
@RequestMapping("/cos/detection-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DetectionInfoController {

    private final IDetectionInfoService detectionInfoService;

    /**
     * 分页获取检测项信息
     *
     * @param page          分页对象
     * @param detectionInfo 检测项信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<DetectionInfo> page, DetectionInfo detectionInfo) {
        return R.ok(detectionInfoService.selectDetectionPage(page, detectionInfo));
    }

    /**
     * 检测项信息详情
     *
     * @param id 检测项ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(detectionInfoService.getById(id));
    }

    /**
     * 检测项信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(detectionInfoService.list());
    }

    /**
     * 新增检测项信息
     *
     * @param detectionInfo 检测项信息
     * @return 结果
     */
    @PostMapping
    public R save(DetectionInfo detectionInfo) {
        detectionInfo.setCode("DE-" + System.currentTimeMillis());
        detectionInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(detectionInfoService.save(detectionInfo));
    }

    /**
     * 修改检测项信息
     *
     * @param detectionInfo 检测项信息
     * @return 结果
     */
    @PutMapping
    public R edit(DetectionInfo detectionInfo) {
        return R.ok(detectionInfoService.updateById(detectionInfo));
    }

    /**
     * 删除检测项信息
     *
     * @param ids ids
     * @return 检测项信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(detectionInfoService.removeByIds(ids));
    }
}
