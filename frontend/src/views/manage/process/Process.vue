<template>
  <a-card :bordered="false" class="card-area">
    <div :class="advanced ? 'search' : null">
      <!-- 搜索区域 -->
      <a-form layout="horizontal">
        <a-row :gutter="15">
          <div :class="advanced ? null: 'fold'">
            <a-col :md="6" :sm="24">
              <a-form-item
                label="检验单编号"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}">
                <a-input v-model="queryParams.code"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item
                label="检验单名称"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}">
                <a-input v-model="queryParams.name"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item
                label="工作站名称"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}">
                <a-input v-model="queryParams.stationName"/>
              </a-form-item>
            </a-col>
            <a-col :md="6" :sm="24">
              <a-form-item
                label="产品物料"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}">
                <a-input v-model="queryParams.materielName"/>
              </a-form-item>
            </a-col>
          </div>
          <span style="float: right; margin-top: 3px;">
            <a-button type="primary" @click="search">查询</a-button>
            <a-button style="margin-left: 8px" @click="reset">重置</a-button>
          </span>
        </a-row>
      </a-form>
    </div>
    <div>
      <div class="operator">
        <a-button type="primary" ghost @click="add">新增</a-button>
        <a-button @click="batchDelete">删除</a-button>
      </div>
      <!-- 表格区域 -->
      <a-table ref="TableInfo"
               :columns="columns"
               :rowKey="record => record.id"
               :dataSource="dataSource"
               :pagination="pagination"
               :loading="loading"
               :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
               :scroll="{ x: 900 }"
               @change="handleTableChange">
        <template slot="titleShow" slot-scope="text, record">
          <template>
            <a-tooltip>
              <template slot="title">
                {{ record.title }}
              </template>
              {{ record.title.slice(0, 8) }} ...
            </a-tooltip>
          </template>
        </template>
        <template slot="operation" slot-scope="text, record">
          <a-icon type="exception" @click="edit(record)" title="填 报" style="margin-left: 15px" v-if="record.checkStatus == 0"></a-icon>
          <a-icon type="cloud" @click="handleprocessViewOpen(record)" title="详 情" style="margin-left: 15px" v-if="record.checkStatus != 0"></a-icon>
        </template>
      </a-table>
    </div>
    <process-add
      v-if="processAdd.visiable"
      @close="handleprocessAddClose"
      @success="handleprocessAddSuccess"
      :processAddVisiable="processAdd.visiable">
    </process-add>
    <process-edit
      ref="processEdit"
      @close="handleprocessEditClose"
      @success="handleprocessEditSuccess"
      :processEditVisiable="processEdit.visiable">
    </process-edit>
    <process-view
      @close="handleprocessViewClose"
      :processShow="processView.visiable"
      :processData="processView.data">
    </process-view>
  </a-card>
</template>

<script>
import RangeDate from '@/components/datetime/RangeDate'
import processAdd from './ProcessAdd.vue'
import processEdit from './ProcessEdit.vue'
import processView from './ProcessView.vue'
import {mapState} from 'vuex'
import moment from 'moment'
moment.locale('zh-cn')

export default {
  name: 'process',
  components: {processAdd, processEdit, RangeDate, processView},
  data () {
    return {
      advanced: false,
      processAdd: {
        visiable: false
      },
      processEdit: {
        visiable: false
      },
      processView: {
        visiable: false,
        data: null
      },
      queryParams: {},
      filteredInfo: null,
      sortedInfo: null,
      paginationInfo: null,
      dataSource: [],
      selectedRowKeys: [],
      loading: false,
      pagination: {
        pageSizeOptions: ['10', '20', '30', '40', '100'],
        defaultCurrent: 1,
        defaultPageSize: 10,
        showQuickJumper: true,
        showSizeChanger: true,
        showTotal: (total, range) => `显示 ${range[0]} ~ ${range[1]} 条记录，共 ${total} 条记录`
      },
      brandList: []
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    columns () {
      return [{
        title: '检验单编码',
        dataIndex: 'code',
        width: 180
      }, {
        title: '检验单名称',
        dataIndex: 'name',
        width: 180
      }, {
        title: '工单名称',
        dataIndex: 'orderName',
        width: 180
      }, {
        title: '检验类型',
        dataIndex: 'checkType',
        customRender: (text, row, index) => {
          switch (text) {
            case '1':
              return <a-tag>首检</a-tag>
            case '2':
              return <a-tag>末检</a-tag>
            case '3':
              return <a-tag>自检</a-tag>
            case '4':
              return <a-tag>巡检</a-tag>
            case '5':
              return <a-tag>点检</a-tag>
            case '6':
              return <a-tag>终检</a-tag>
            default:
              return '- -'
          }
        },
        width: 180
      }, {
        title: '产品物料名称',
        dataIndex: 'materielName',
        width: 180
      }, {
        title: '规格型号',
        dataIndex: 'model',
        width: 180
      }, {
        title: '单位',
        dataIndex: 'unit',
        width: 180
      }, {
        title: '检测数量',
        dataIndex: 'checkNum',
        width: 180
      }, {
        title: '检测结果',
        dataIndex: 'checkStatus',
        customRender: (text, row, index) => {
          switch (text) {
            case '0':
              return <a-tag>等待检测</a-tag>
            case '1':
              return <a-tag color="green">通过</a-tag>
            case '2':
              return <a-tag color="red">不通过</a-tag>
            default:
              return '- -'
          }
        },
        width: 180
      }, {
        title: '检测人员',
        dataIndex: 'staffName',
        customRender: (text, row, index) => {
          if (text !== null) {
            return text
          } else {
            return '- -'
          }
        },
        width: 180
      }, {
        title: '检测日期',
        dataIndex: 'checkDate',
        customRender: (text, row, index) => {
          if (text !== null) {
            return text
          } else {
            return '- -'
          }
        },
        width: 180
      }, {
        title: '单据状态',
        dataIndex: 'receiptStatus',
        customRender: (text, row, index) => {
          switch (text) {
            case '0':
              return <a-tag>已创建</a-tag>
            case '1':
              return <a-tag>检测中</a-tag>
            case '2':
              return <a-tag>已完成</a-tag>
            default:
              return '- -'
          }
        },
        width: 180
      }, {
        title: '操作',
        dataIndex: 'operation',
        fixed: 'right',
        scopedSlots: {customRender: 'operation'},
        width: 100
      }]
    }
  },
  mounted () {
    this.fetch()
  },
  methods: {
    handleprocessViewClose () {
      this.processView.visiable = false
    },
    handleprocessViewOpen (row) {
      this.processView.data = row
      this.processView.visiable = true
    },
    selectShopList () {
      this.$get(`/cos/brand-info/list`).then((r) => {
        this.brandList = r.data.data
      })
    },
    editStatus (row, status) {
      this.$post('/cos/process-info/account/status', { processId: row.id, status }).then((r) => {
        this.$message.success('修改成功')
        this.fetch()
      })
    },
    onSelectChange (selectedRowKeys) {
      this.selectedRowKeys = selectedRowKeys
    },
    toggleAdvanced () {
      this.advanced = !this.advanced
    },
    add () {
      this.processAdd.visiable = true
    },
    handleprocessAddClose () {
      this.processAdd.visiable = false
    },
    handleprocessAddSuccess () {
      this.processAdd.visiable = false
      this.$message.success('新增过程检测成功')
      this.search()
    },
    edit (record) {
      this.$refs.processEdit.setFormValues(record)
      this.processEdit.visiable = true
    },
    handleprocessEditClose () {
      this.processEdit.visiable = false
    },
    handleprocessEditSuccess () {
      this.processEdit.visiable = false
      this.$message.success('修改过程检测成功')
      this.search()
    },
    handleDeptChange (value) {
      this.queryParams.deptId = value || ''
    },
    batchDelete () {
      if (!this.selectedRowKeys.length) {
        this.$message.warning('请选择需要删除的记录')
        return
      }
      let that = this
      this.$confirm({
        title: '确定删除所选中的记录?',
        content: '当您点击确定按钮后，这些记录将会被彻底删除',
        centered: true,
        onOk () {
          let ids = that.selectedRowKeys.join(',')
          that.$delete('/cos/process-check-info/' + ids).then(() => {
            that.$message.success('删除成功')
            that.selectedRowKeys = []
            that.search()
          })
        },
        onCancel () {
          that.selectedRowKeys = []
        }
      })
    },
    search () {
      let {sortedInfo, filteredInfo} = this
      let sortField, sortOrder
      // 获取当前列的排序和列的过滤规则
      if (sortedInfo) {
        sortField = sortedInfo.field
        sortOrder = sortedInfo.order
      }
      this.fetch({
        sortField: sortField,
        sortOrder: sortOrder,
        ...this.queryParams,
        ...filteredInfo
      })
    },
    reset () {
      // 取消选中
      this.selectedRowKeys = []
      // 重置分页
      this.$refs.TableInfo.pagination.current = this.pagination.defaultCurrent
      if (this.paginationInfo) {
        this.paginationInfo.current = this.pagination.defaultCurrent
        this.paginationInfo.pageSize = this.pagination.defaultPageSize
      }
      // 重置列过滤器规则
      this.filteredInfo = null
      // 重置列排序规则
      this.sortedInfo = null
      // 重置查询参数
      this.queryParams = {}
      this.fetch()
    },
    handleTableChange (pagination, filters, sorter) {
      // 将这三个参数赋值给Vue data，用于后续使用
      this.paginationInfo = pagination
      this.filteredInfo = filters
      this.sortedInfo = sorter

      this.fetch({
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...this.queryParams,
        ...filters
      })
    },
    fetch (params = {}) {
      // 显示loading
      this.loading = true
      if (this.paginationInfo) {
        // 如果分页信息不为空，则设置表格当前第几页，每页条数，并设置查询分页参数
        this.$refs.TableInfo.pagination.current = this.paginationInfo.current
        this.$refs.TableInfo.pagination.pageSize = this.paginationInfo.pageSize
        params.size = this.paginationInfo.pageSize
        params.current = this.paginationInfo.current
      } else {
        // 如果分页信息为空，则设置为默认值
        params.size = this.pagination.defaultPageSize
        params.current = this.pagination.defaultCurrent
      }
      if (params.brand === undefined) {
        delete params.brand
      }
      this.$get('/cos/process-check-info/page', {
        ...params
      }).then((r) => {
        let data = r.data.data
        const pagination = {...this.pagination}
        pagination.total = data.total
        this.dataSource = data.records
        this.pagination = pagination
        // 数据加载完毕，关闭loading
        this.loading = false
      })
    }
  },
  watch: {}
}
</script>
<style lang="less" scoped>
@import "../../../../static/less/Common";
</style>
