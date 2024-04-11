package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.ClienteleInfoMapper;
import cc.mrbird.febs.cos.dao.StaffInfoMapper;
import cc.mrbird.febs.cos.dao.StationInfoMapper;
import cc.mrbird.febs.cos.entity.ClienteleInfo;
import cc.mrbird.febs.cos.entity.MaterielInfo;
import cc.mrbird.febs.cos.dao.MaterielInfoMapper;
import cc.mrbird.febs.cos.entity.StaffInfo;
import cc.mrbird.febs.cos.entity.StationInfo;
import cc.mrbird.febs.cos.service.IIncomeCheckInfoService;
import cc.mrbird.febs.cos.service.IMaterielInfoService;
import cc.mrbird.febs.cos.service.IProcessCheckInfoService;
import cc.mrbird.febs.cos.service.IShipCheckInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Fank gmail - fan1ke2ke@gmail.com
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MaterielInfoServiceImpl extends ServiceImpl<MaterielInfoMapper, MaterielInfo> implements IMaterielInfoService {

    private final StaffInfoMapper staffInfoMapper;

    private final ClienteleInfoMapper clienteleInfoMapper;

    private final StationInfoMapper stationInfoMapper;

    private final MaterielInfoMapper materielInfoMapper;

    private final IIncomeCheckInfoService incomeCheckInfoService;

    private final IProcessCheckInfoService processCheckInfoService;

    private final IShipCheckInfoService shipCheckInfoService;

    /**
     * 分页获取物料信息
     *
     * @param page         分页对象
     * @param materielInfo 物料信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectMaterielPage(Page<MaterielInfo> page, MaterielInfo materielInfo) {
        return baseMapper.selectMaterielPage(page, materielInfo);
    }

    /**
     * 查询主页信息
     *
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> homeData() {
        // 管理员展示信息
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();

        // 本月检测数量/单
        Integer incomeMonth = baseMapper.selectIncomeMonth();
        // 本月通过单数
        Integer workOrderMonth = baseMapper.selectWorkOrderMonth();
        // 本年检测数量/单
        Integer incomeYear = baseMapper.selectIncomeYear();
        // 本年通过单数
        Integer workOrderYear = baseMapper.selectWorkOrderYear();
        // 员工数量
        Integer staffNum = staffInfoMapper.selectCount(Wrappers.<StaffInfo>lambdaQuery());
        // 客户数量
        Integer clienteleNum = clienteleInfoMapper.selectCount(Wrappers.<ClienteleInfo>lambdaQuery());
        // 工作站量
        Integer stationNum = stationInfoMapper.selectCount(Wrappers.<StationInfo>lambdaQuery());
        // 物料数量
        Integer materielNum = materielInfoMapper.selectCount(Wrappers.<MaterielInfo>lambdaQuery());
        // 十天内检测记录
        List<LinkedHashMap<String, Object>> paymentRecord1 = baseMapper.selectPaymentRecord1();
        List<LinkedHashMap<String, Object>> paymentRecord2 = baseMapper.selectPaymentRecord2();
        List<LinkedHashMap<String, Object>> paymentRecord3 = baseMapper.selectPaymentRecord3();
        // 十天内通过统计
        List<LinkedHashMap<String, Object>> orderRecord1 = baseMapper.selectOrderRecord1();
        List<LinkedHashMap<String, Object>> orderRecord2 = baseMapper.selectOrderRecord2();
        List<LinkedHashMap<String, Object>> orderRecord3 = baseMapper.selectOrderRecord3();
        result.put("incomeMonth", incomeMonth);
        result.put("workOrderMonth", workOrderMonth);
        result.put("incomeYear", incomeYear);
        result.put("workOrderYear", workOrderYear);

        result.put("clienteleNum", clienteleNum);
        result.put("staffNum", staffNum);
        result.put("stationNum", stationNum);
        result.put("materielNum", materielNum);

        result.put("paymentRecord1", paymentRecord1);
        result.put("paymentRecord2", paymentRecord2);
        result.put("paymentRecord3", paymentRecord3);

        result.put("orderRecord1", orderRecord1);
        result.put("orderRecord2", orderRecord2);
        result.put("orderRecord3", orderRecord3);
        return result;
    }
}
