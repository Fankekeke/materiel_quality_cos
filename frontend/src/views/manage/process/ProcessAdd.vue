<template>
  <a-modal v-model="show" title="新增过程检测信息" @cancel="onClose" :width="950">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
        提交
      </a-button>
    </template>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="8">
          <a-form-item label='检验单名称' v-bind="formItemLayout">
            <a-input v-decorator="[
            'name',
            { rules: [{ required: true, message: '请输入检验单名称!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='检验类型' v-bind="formItemLayout">
            <a-select v-decorator="[
              'checkType',
              { rules: [{ required: true, message: '请输入检验类型!' }] }
              ]">
              <a-select-option value="1">首检</a-select-option>
              <a-select-option value="2">末检</a-select-option>
              <a-select-option value="3">自检</a-select-option>
              <a-select-option value="4">巡检</a-select-option>
              <a-select-option value="5">点检</a-select-option>
              <a-select-option value="6">终检</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-divider orientation="left">
          <span style="font-size: 13px;font-family: SimHei">工单与工作站</span>
        </a-divider>
        <a-col :span="8">
          <a-form-item label='选择工单' v-bind="formItemLayout">
            <a-select @change="orderChange" v-decorator="[
              'orderId',
              { rules: [{ required: true, message: '请输入工单!' }] }
              ]">
              <a-select-option :value="item.id" v-for="(item, index) in orderList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='工单名称' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'orderName',
            { rules: [{ required: true, message: '请输入工单名称!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='工单编码' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'orderCode',
            { rules: [{ required: true, message: '请输入工单编码!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='选择工作站' v-bind="formItemLayout">
            <a-select @change="stationChange" v-decorator="[
              'stationId',
              { rules: [{ required: true, message: '请输入工作站!' }] }
              ]">
              <a-select-option :value="item.id" v-for="(item, index) in stationList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='工作站名称' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'stationName',
            { rules: [{ required: true, message: '请输入工作站名称!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='工作站编号' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'stationCode',
            { rules: [{ required: true, message: '请输入工作站编号!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='检测数量' disabled v-bind="formItemLayout">
            <a-input-number :min="0" :max="99999" v-decorator="[
            'checkNum',
            { rules: [{ required: true, message: '请输入检测数量!' }] }
            ]" style="width: 100%"/>
          </a-form-item>
        </a-col>
        <a-divider orientation="left">
          <span style="font-size: 13px;font-family: SimHei">更多信息</span>
        </a-divider>
        <a-col :span="6">
          <a-form-item label='选择物料' v-bind="formItemLayout">
            <a-select @change="materielChange" v-decorator="[
              'materielId',
              { rules: [{ required: true, message: '请输入物料!' }] }
              ]">
              <a-select-option :value="item.id" v-for="(item, index) in materielList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="6">
          <a-form-item label='产品物料编码' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'materielCode',
            { rules: [{ required: true, message: '请输入产品物料!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="6">
          <a-form-item label='产品物料名称' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'materielName',
            { rules: [{ required: true, message: '请输入产品物料名称!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="6">
          <a-form-item label='单位' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'unit'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='规格型号' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'model'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='工序编码' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'processCode'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='工序名称' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'processName',
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='任务编号' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'taskCode',
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='任务名称' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'taskName',
            ]"/>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
  </a-modal>
</template>

<script>
import {mapState} from 'vuex'
import moment from 'moment'
moment.locale('zh-cn')
function getBase64 (file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'processAdd',
  props: {
    processAddVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.processAddVisiable
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      fileList: [],
      previewVisible: false,
      previewImage: '',
      pharmacyList: [],
      shopList: [],
      processTypeList: [],
      brandList: [],
      materielList: [],
      stationList: [],
      orderList: []
    }
  },
  mounted () {
    this.selectMaterielList()
    this.selectOrderList()
    this.selectStationList()
  },
  methods: {
    orderChange (value) {
      if (value) {
        this.orderList.forEach(item => {
          if (item.id === value) {
            this.form.setFieldsValue({
              orderCode: item.code,
              orderName: item.name
            })
          }
        })
      }
    },
    materielChange (value) {
      if (value) {
        this.materielList.forEach(item => {
          if (item.id === value) {
            this.form.setFieldsValue({
              materielCode: item.code,
              materielName: item.name,
              unit: item.unit,
              model: item.model
            })
          }
        })
      }
    },
    stationChange (value) {
      if (value) {
        this.stationList.forEach(item => {
          if (item.id === value) {
            this.form.setFieldsValue({
              stationCode: item.code,
              stationName: item.name
            })
          }
        })
      }
    },
    selectMaterielList () {
      this.$get(`/cos/detection-template-info/materiel`, {type: '2'}).then((r) => {
        this.materielList = r.data.data
      })
    },
    selectOrderList () {
      this.$get(`/cos/order-info/list`).then((r) => {
        this.orderList = r.data.data
      })
    },
    selectStationList () {
      this.$get(`/cos/station-info/list`).then((r) => {
        this.stationList = r.data.data
      })
    },
    handleCancel () {
      this.previewVisible = false
    },
    async handlePreview (file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj)
      }
      this.previewImage = file.url || file.preview
      this.previewVisible = true
    },
    picHandleChange ({ fileList }) {
      this.fileList = fileList
    },
    reset () {
      this.loading = false
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    handleSubmit () {
      this.form.validateFields((err, values) => {
        // 获取图片List
        let images = []
        this.fileList.forEach(image => {
          images.push(image.response)
        })
        if (!err) {
          values.images = images.length > 0 ? images.join(',') : null
          this.loading = true
          this.$post('/cos/process-check-info', {
            ...values
          }).then((r) => {
            this.reset()
            this.$emit('success')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
