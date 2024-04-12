package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.DetectionChcekInfo;
import cc.mrbird.febs.cos.service.IDetectionChcekInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
@RequestMapping("/cos/detection-chcek-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DetectionChcekInfoController {

    private final IDetectionChcekInfoService detectionChcekInfoService;


    /**
     * 检测项填报信息详情
     *
     * @param id 检测项填报ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(detectionChcekInfoService.getById(id));
    }

    /**
     * 根据编号获取检测项结果
     *
     * @param code 编号
     * @return 结果
     */
    @GetMapping("/byCode")
    public R selectListByCode(String code) {
        return R.ok(detectionChcekInfoService.list(Wrappers.<DetectionChcekInfo>lambdaQuery().eq(DetectionChcekInfo::getCode, code)));
    }

    /**
     * 检测项填报信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(detectionChcekInfoService.list());
    }

    /**
     * 新增检测项填报信息
     *
     * @param detectionChcekInfo 检测项填报信息
     * @return 结果
     */
    @PostMapping
    public R save(DetectionChcekInfo detectionChcekInfo) {
        return R.ok(detectionChcekInfoService.save(detectionChcekInfo));
    }

    /**
     * 修改检测项填报信息
     *
     * @param detectionChcekInfo 检测项填报信息
     * @return 结果
     */
    @PutMapping
    public R edit(DetectionChcekInfo detectionChcekInfo) {
        return R.ok(detectionChcekInfoService.updateById(detectionChcekInfo));
    }

    /**
     * 删除检测项填报信息
     *
     * @param ids ids
     * @return 检测项填报信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(detectionChcekInfoService.removeByIds(ids));
    }
}
