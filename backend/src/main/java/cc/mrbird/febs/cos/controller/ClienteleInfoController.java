package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ClienteleInfo;
import cc.mrbird.febs.cos.service.IClienteleInfoService;
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
@RequestMapping("/cos/clientele-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteleInfoController {

    private final IClienteleInfoService clienteleInfoService;

    /**
     * 分页获取客户信息
     *
     * @param page          分页对象
     * @param clienteleInfo 客户信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ClienteleInfo> page, ClienteleInfo clienteleInfo) {
        return R.ok(clienteleInfoService.selectClientelePage(page, clienteleInfo));
    }

    /**
     * 客户信息详情
     *
     * @param id 客户ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(clienteleInfoService.getById(id));
    }

    /**
     * 客户信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(clienteleInfoService.list());
    }

    /**
     * 新增客户信息
     *
     * @param clienteleInfo 客户信息
     * @return 结果
     */
    @PostMapping
    public R save(ClienteleInfo clienteleInfo) {
        clienteleInfo.setCode("CL-" + System.currentTimeMillis());
        clienteleInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(clienteleInfoService.save(clienteleInfo));
    }

    /**
     * 修改客户信息
     *
     * @param clienteleInfo 客户信息
     * @return 结果
     */
    @PutMapping
    public R edit(ClienteleInfo clienteleInfo) {
        return R.ok(clienteleInfoService.updateById(clienteleInfo));
    }

    /**
     * 删除客户信息
     *
     * @param ids ids
     * @return 客户信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(clienteleInfoService.removeByIds(ids));
    }
}
