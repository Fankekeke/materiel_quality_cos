package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.FlawInfo;
import cc.mrbird.febs.cos.service.IFlawInfoService;
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
@RequestMapping("/cos/flaw-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FlawInfoController {

    private final IFlawInfoService flawInfoService;

    /**
     * 分页获取常见缺陷信息
     *
     * @param page     分页对象
     * @param flawInfo 常见缺陷信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<FlawInfo> page, FlawInfo flawInfo) {
        return R.ok(flawInfoService.selectFlawPage(page, flawInfo));
    }

    /**
     * 常见缺陷信息详情
     *
     * @param id 常见缺陷ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(flawInfoService.getById(id));
    }

    /**
     * 常见缺陷信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(flawInfoService.list());
    }

    /**
     * 新增常见缺陷信息
     *
     * @param flawInfo 常见缺陷信息
     * @return 结果
     */
    @PostMapping
    public R save(FlawInfo flawInfo) {
        flawInfo.setCode("FA-" + System.currentTimeMillis());
        flawInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(flawInfoService.save(flawInfo));
    }

    /**
     * 修改常见缺陷信息
     *
     * @param flawInfo 常见缺陷信息
     * @return 结果
     */
    @PutMapping
    public R edit(FlawInfo flawInfo) {
        return R.ok(flawInfoService.updateById(flawInfo));
    }

    /**
     * 删除常见缺陷信息
     *
     * @param ids ids
     * @return 常见缺陷信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(flawInfoService.removeByIds(ids));
    }
}
