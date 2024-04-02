package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ProcessCheckInfo;
import cc.mrbird.febs.cos.entity.ShipCheckInfo;
import cc.mrbird.febs.cos.service.IShipCheckInfoService;
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
@RequestMapping("/cos/ship-check-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShipCheckInfoController {

    private final IShipCheckInfoService shipCheckInfoService;

    /**
     * 分页获取出货检验信息
     *
     * @param page          分页对象
     * @param shipCheckInfo 出货检验信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ShipCheckInfo> page, ShipCheckInfo shipCheckInfo) {
        return R.ok(shipCheckInfoService.selectShipCheckPage(page, shipCheckInfo));
    }

    /**
     * 出货检验信息详情
     *
     * @param id 出货检验ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(shipCheckInfoService.getById(id));
    }

    /**
     * 出货检验信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(shipCheckInfoService.list());
    }

    /**
     * 填报出货检测信息
     *
     * @param shipCheckInfo 出货检测
     * @return 结果
     * @throws FebsException
     */
    @PostMapping("/additional")
    public R additional(ShipCheckInfo shipCheckInfo) throws FebsException {

        ShipCheckInfo checkInfo = shipCheckInfoService.getById(shipCheckInfo.getId());
        // 本次检测数量校验
        if (checkInfo.getShipNum() < shipCheckInfo.getCheckNum()) {
            throw new FebsException("检测数量不能大于发货数量");
        }
        // 不合格数量校验
        if (shipCheckInfo.getCheckNum() < shipCheckInfo.getUnqualifiedNum()) {
            throw new FebsException("不合格数量不能大于本次检测数量");
        }

        // 缺陷率不能大于100
        BigDecimal total = NumberUtil.add(shipCheckInfo.getFatalDefectRate(), shipCheckInfo.getCriticalDefectRate(), shipCheckInfo.getMinorDefectRate());
        System.out.println(total.compareTo(new BigDecimal(100)));
        if (total.compareTo(new BigDecimal(100)) > 0) {
            throw new FebsException("致命缺陷率、严重缺陷率、轻微缺陷率不能超过100%");
        }

        // 缺陷数量校验
        Integer totalNum = shipCheckInfo.getFatalDefectNum() + shipCheckInfo.getCriticalDefectNum() + shipCheckInfo.getMinorDefectNum();
        if (!totalNum.equals(shipCheckInfo.getUnqualifiedNum())) {
            throw new FebsException("缺陷数量校验必须等于不合格数量");
        }
        shipCheckInfo.setReceiptStatus("2");
        shipCheckInfo.setCheckDate(DateUtil.formatDateTime(new Date()));
        return R.ok(shipCheckInfoService.updateById(shipCheckInfo));
    }

    /**
     * 新增出货检验信息
     *
     * @param shipCheckInfo 出货检验信息
     * @return 结果
     */
    @PostMapping
    public R save(ShipCheckInfo shipCheckInfo) {
        shipCheckInfo.setCode("SP-" + System.currentTimeMillis());
        return R.ok(shipCheckInfoService.save(shipCheckInfo));
    }

    /**
     * 修改出货检验信息
     *
     * @param shipCheckInfo 出货检验信息
     * @return 结果
     */
    @PutMapping
    public R edit(ShipCheckInfo shipCheckInfo) {
        return R.ok(shipCheckInfoService.updateById(shipCheckInfo));
    }

    /**
     * 删除出货检验信息
     *
     * @param ids ids
     * @return 出货检验信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(shipCheckInfoService.removeByIds(ids));
    }
}
