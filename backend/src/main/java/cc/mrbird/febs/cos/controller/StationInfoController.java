package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.StationInfo;
import cc.mrbird.febs.cos.service.IStationInfoService;
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
@RequestMapping("/cos/station-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StationInfoController {

    private final IStationInfoService stationInfoService;

    /**
     * 分页获取工作站信息
     *
     * @param page        分页对象
     * @param stationInfo 工作站信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<StationInfo> page, StationInfo stationInfo) {
        return R.ok(stationInfoService.selectStationPage(page, stationInfo));
    }

    /**
     * 工作站信息详情
     *
     * @param id 工作站ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(stationInfoService.getById(id));
    }

    /**
     * 工作站信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(stationInfoService.list());
    }

    /**
     * 新增工作站信息
     *
     * @param stationInfo 工作站信息
     * @return 结果
     */
    @PostMapping
    public R save(StationInfo stationInfo) {
        stationInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        stationInfo.setCode("STA-" + System.currentTimeMillis());
        return R.ok(stationInfoService.save(stationInfo));
    }

    /**
     * 修改工作站信息
     *
     * @param stationInfo 工作站信息
     * @return 结果
     */
    @PutMapping
    public R edit(StationInfo stationInfo) {
        return R.ok(stationInfoService.updateById(stationInfo));
    }

    /**
     * 删除工作站信息
     *
     * @param ids ids
     * @return 工作站信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(stationInfoService.removeByIds(ids));
    }
}
