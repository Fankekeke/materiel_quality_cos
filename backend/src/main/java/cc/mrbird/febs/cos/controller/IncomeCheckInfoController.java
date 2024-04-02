package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.IncomeCheckInfo;
import cc.mrbird.febs.cos.service.IIncomeCheckInfoService;
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
@RequestMapping("/cos/income-check-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IncomeCheckInfoController {

    private final IIncomeCheckInfoService incomeCheckInfoService;

    /**
     * 分页获取来料检验信息
     *
     * @param page            分页对象
     * @param incomeCheckInfo 来料检验信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<IncomeCheckInfo> page, IncomeCheckInfo incomeCheckInfo) {
        return R.ok(incomeCheckInfoService.selectIncomeCheckPage(page, incomeCheckInfo));
    }

    /**
     * 来料检验信息详情
     *
     * @param id 来料检验ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(incomeCheckInfoService.getById(id));
    }

    /**
     * 来料检验信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(incomeCheckInfoService.list());
    }

    /**
     * 新增来料检验信息
     *
     * @param incomeCheckInfo 来料检验信息
     * @return 结果
     */
    @PostMapping
    public R save(IncomeCheckInfo incomeCheckInfo) {
        incomeCheckInfo.setCode("ICC-" + System.currentTimeMillis());
        return R.ok(incomeCheckInfoService.save(incomeCheckInfo));
    }

    /**
     * 填报来料检测信息
     *
     * @param incomeCheckInfo 来料检测
     * @return 结果
     * @throws FebsException
     */
    @PostMapping("/additional")
    public R additional(IncomeCheckInfo incomeCheckInfo) throws FebsException {

        IncomeCheckInfo checkInfo = incomeCheckInfoService.getById(incomeCheckInfo.getId());
        // 本次检测数量校验
        if (checkInfo.getReceiveQuantity() < incomeCheckInfo.getCheckQuantity()) {
            throw new FebsException("本次检测数量不能大于本次接收数量");
        }
        // 不合格数量校验
        if (incomeCheckInfo.getCheckQuantity() < incomeCheckInfo.getUnqualifiedQuantity()) {
            throw new FebsException("不合格数量不能大于本次检测数量");
        }

        // 缺陷率不能大于100
        BigDecimal total = NumberUtil.add(incomeCheckInfo.getFatalDefectRate(), incomeCheckInfo.getCriticalDefectRate(), incomeCheckInfo.getMinorDefectRate());
        System.out.println(total.compareTo(new BigDecimal(100)));
        if (total.compareTo(new BigDecimal(100)) > 0) {
            throw new FebsException("致命缺陷率、严重缺陷率、轻微缺陷率不能超过100%");
        }

        // 缺陷数量校验
        Integer totalNum = incomeCheckInfo.getFatalDefectNum() + incomeCheckInfo.getCriticalDefectNum() + incomeCheckInfo.getMinorDefectNum();
        if (!totalNum.equals(incomeCheckInfo.getUnqualifiedQuantity())) {
            throw new FebsException("缺陷数量校验必须等于不合格数量");
        }
        incomeCheckInfo.setReceiptStatus("2");
        incomeCheckInfo.setCheckDate(DateUtil.formatDateTime(new Date()));
        return R.ok(incomeCheckInfoService.updateById(incomeCheckInfo));
    }

    /**
     * 修改来料检验信息
     *
     * @param incomeCheckInfo 来料检验信息
     * @return 结果
     */
    @PutMapping
    public R edit(IncomeCheckInfo incomeCheckInfo) {
        return R.ok(incomeCheckInfoService.updateById(incomeCheckInfo));
    }

    /**
     * 删除来料检验信息
     *
     * @param ids ids
     * @return 来料检验信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(incomeCheckInfoService.removeByIds(ids));
    }
}
