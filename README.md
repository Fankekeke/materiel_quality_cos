### 基于SpringBoot + Vue物料质量检测系统.

耗材入库质检、IQC进料检验、质量追溯系统

##### 基础档案与标准定义
###### 检测项/模板管理： 自定义耗材检测指标与分级标准，通过数字化模板实现不同物料检测方案的快速调用。

###### 物料/供应商管理： 统一维护耗材属性与供应来源，建立完整的供应商评价体系，从源头把控物料质量。

###### 常见缺陷库： 预设标准缺陷分类与代码，规范检测人员对异常问题的描述，为后续质量分析提供结构化数据。

##### 生产组织与资源配置
###### 客户/工单管理： 关联客户需求并下达检测任务，全过程追踪工单执行进度，确保检测业务有序流转。

###### 员工/工作站管理： 科学配置检测人员权限与设备工位，实现人、机、料、法、环的数字化协同管理。

##### 全链路质量检验体系
###### 来料检测 (IQC)： 严控入库第一道关卡，对到货耗材进行抽样核验，防止不合格品流入生产或仓储环节。

###### 过程检验 (IPQC)： 实时监控生产加工中的耗材状态，及时发现并拦截工序异常，降低成品的质量风险。

###### 出货检验 (OQQC)： 完成成品包装后的终审抽检，确保交付至客户手中的耗材完全符合合同质量标准。

##### 数字化支撑与统计决策
###### 统计看板： 聚合合格率、缺陷趋势及供应商表现等核心指标，通过可视化图表辅助管理层进行质量决策。

###### 系统管理： 负责角色权限分配、系统日志审计及基础参数设置，保障整个检测平台运行的安全与稳定。

#### 安装环境

JAVA 环境 

Node.js环境 [https://nodejs.org/en/] 选择14.17

Yarn 打开cmd， 输入npm install -g yarn !!!必须安装完毕nodejs

Mysql 数据库 [https://blog.csdn.net/qq_40303031/article/details/88935262] 一定要把账户和密码记住

redis

Idea 编译器 [https://blog.csdn.net/weixin_44505194/article/details/104452880]

WebStorm OR VScode 编译器 [https://www.jianshu.com/p/d63b5bae9dff]

#### 采用技术及功能

后端：SpringBoot、MybatisPlus、MySQL、Redis、
前端：Vue、Apex、Antd、Axios

平台前端：vue(框架) + vuex(全局缓存) + rue-router(路由) + axios(请求插件) + apex(图表)  + antd-ui(ui组件)

平台后台：springboot(框架) + redis(缓存中间件) + shiro(权限中间件) + mybatisplus(orm) + restful风格接口 + mysql(数据库)

开发环境：windows10 or windows7 ， vscode or webstorm ， idea + lambok

> 管理员: 客户管理、检测项管理、检测模板、常见缺陷、物料管理、工单管理、员工管理、工作站管理、供应商信息、来料检测、过程检验、出货检验、系统管理、统计看板


#### 前台启动方式
安装所需文件 yarn install 
运行 yarn run dev

#### 后端启动方式

1.首先启动redis，进入redis目录终端。输入redis-server回车
2.导入sql文件，修改数据库与redis连接配置
3.idea中启动后端项目

#### 默认后台账户密码
[管理员]
admin
1234qwer


#### 项目截图

|  |  |
|---------------------|---------------------|
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934504638.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934405280.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934490065.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934394961.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934481870.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934377201.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934471611.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934359291.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934460695.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934345379.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934441477.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934334801.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934425388.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934323809.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934416369.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1712934314638.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/work/936e9baf53eb9a217af4f89c616dc19.png) |



#### 演示视频

暂无

#### 获取方式

Email: fan1ke2ke@gmail.com

WeChat: `Storm_Berserker`

`附带部署与讲解服务，因为要恰饭资源非免费，伸手党勿扰，谢谢理解😭`

> 1.项目纯原创，不做二手贩子 2.一次购买终身有效 3.项目讲解持续到答辩结束 4.非常负责的答辩指导 5.**黑奴价格**

> 项目部署调试不好包退！功能逻辑没讲明白包退！

#### 其它资源

[2025年-答辩顺利通过-客户评价🍜](https://berserker287.github.io/2025/06/18/2025%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2024年-答辩顺利通过-客户评价👻](https://berserker287.github.io/2024/06/06/2024%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2023年-答辩顺利通过-客户评价🐢](https://berserker287.github.io/2023/06/14/2023%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2022年-答辩通过率100%-客户评价🐣](https://berserker287.github.io/2022/05/25/%E9%A1%B9%E7%9B%AE%E4%BA%A4%E6%98%93%E8%AE%B0%E5%BD%95/)

[毕业答辩导师提问的高频问题](https://berserker287.github.io/2023/06/13/%E6%AF%95%E4%B8%9A%E7%AD%94%E8%BE%A9%E5%AF%BC%E5%B8%88%E6%8F%90%E9%97%AE%E7%9A%84%E9%AB%98%E9%A2%91%E9%97%AE%E9%A2%98/)

[50个高频答辩问题-技术篇](https://berserker287.github.io/2023/06/13/50%E4%B8%AA%E9%AB%98%E9%A2%91%E7%AD%94%E8%BE%A9%E9%97%AE%E9%A2%98-%E6%8A%80%E6%9C%AF%E7%AF%87/)

[计算机毕设答辩时都会问到哪些问题？](https://www.zhihu.com/question/31020988)

[计算机专业毕业答辩小tips](https://zhuanlan.zhihu.com/p/145911029)


#### 接JAVAWEB毕设，纯原创，价格公道，诚信第一

`网站建设、小程序、H5、APP、各种系统 选题+开题报告+任务书+程序定制+安装调试+项目讲解+论文+答辩PPT`

More info: [悲伤的橘子树](https://berserker287.github.io/)

<p><img align="center" src="https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/%E5%90%88%E4%BD%9C%E7%89%A9%E6%96%99%E6%A0%B7%E5%BC%8F%20(3).png" alt="fankekeke" /></p>
