package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.IncomeCheckInfo;
import cc.mrbird.febs.cos.entity.ProcessCheckInfo;
import cc.mrbird.febs.cos.service.IProcessCheckInfoService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/process-check-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProcessCheckInfoController {

    private final IProcessCheckInfoService processCheckInfoService;

    /**
     * 分页获取过程检验信息
     *
     * @param page             分页对象
     * @param processCheckInfo 过程检验信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ProcessCheckInfo> page, ProcessCheckInfo processCheckInfo) {
        return R.ok(processCheckInfoService.selectProcessPage(page, processCheckInfo));
    }

    /**
     * 过程检验信息详情
     *
     * @param id 过程检验ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(processCheckInfoService.getById(id));
    }

    /**
     * 过程检验信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(processCheckInfoService.list());
    }

    /**
     * 填报来料检测信息
     *
     * @param processCheckInfo 来料检测
     * @return 结果
     * @throws FebsException
     */
    @PostMapping("/additional")
    public R additional(ProcessCheckInfo processCheckInfo) throws FebsException {

        ProcessCheckInfo checkInfo = processCheckInfoService.getById(processCheckInfo.getId());
        // 不合格数量与合格数量校验
        Integer totalCount = processCheckInfo.getUnqualifiedNum() + processCheckInfo.getQualifiedNum();
        if (!checkInfo.getCheckNum().equals(totalCount)) {
            throw new FebsException("不合格数量+合格品数量要等于本次检测数量");
        }

        // 缺陷数量校验
        Integer totalNum = processCheckInfo.getFatalDefectNum() + processCheckInfo.getCriticalDefectNum() + processCheckInfo.getMinorDefectNum();
        if (!totalNum.equals(processCheckInfo.getUnqualifiedNum())) {
            throw new FebsException("缺陷数量校验必须等于不合格数量");
        }
        processCheckInfo.setReceiptStatus("2");
        processCheckInfo.setCheckDate(DateUtil.formatDateTime(new Date()));
        return R.ok(processCheckInfoService.updateById(processCheckInfo));
    }

    /**
     * 新增过程检验信息
     *
     * @param processCheckInfo 过程检验信息
     * @return 结果
     */
    @PostMapping
    public R save(ProcessCheckInfo processCheckInfo) {
        processCheckInfo.setCode("PCS-" + System.currentTimeMillis());
        return R.ok(processCheckInfoService.save(processCheckInfo));
    }

    /**
     * 修改过程检验信息
     *
     * @param processCheckInfo 过程检验信息
     * @return 结果
     */
    @PutMapping
    public R edit(ProcessCheckInfo processCheckInfo) {
        return R.ok(processCheckInfoService.updateById(processCheckInfo));
    }

    /**
     * 删除过程检验信息
     *
     * @param ids ids
     * @return 过程检验信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(processCheckInfoService.removeByIds(ids));
    }
}
