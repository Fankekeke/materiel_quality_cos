<template>
  <a-modal v-model="show" title="新增出货检测信息" @cancel="onClose" :width="950">
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
        <a-divider orientation="left">
          <span style="font-size: 13px;font-family: SimHei">物料与客户</span>
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
          <a-form-item label=选择客户 v-bind="formItemLayout">
            <a-select @change="clienteleChange" v-decorator="[
              'clienteleId',
              { rules: [{ required: true, message: '请输入客户!' }] }
              ]">
              <a-select-option :value="item.id" v-for="(item, index) in clienteleList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='客户名称' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'clienteleName'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='客户编号' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'clienteleCode',
            ]"/>
          </a-form-item>
        </a-col>
        <a-divider orientation="left">
          <span style="font-size: 13px;font-family: SimHei">检测情况</span>
        </a-divider>
        <a-col :span="8">
          <a-form-item label='批次号' disabled v-bind="formItemLayout">
            <a-input v-decorator="[
            'batchNumber',
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='发货数量' disabled v-bind="formItemLayout">
            <a-input-number :min="0" :max="99999" v-decorator="[
            'shipNum',
            { rules: [{ required: true, message: '请输入发货数量!' }] }
            ]" style="width: 100%"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='出货日期' disabled v-bind="formItemLayout">
            <a-date-picker style="width: 100%;" v-decorator="[
            'shipDate',
            { rules: [{ required: true, message: '请输入出货日期!' }] }
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
  name: 'shipAdd',
  props: {
    shipAddVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.shipAddVisiable
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
      shipTypeList: [],
      brandList: [],
      materielList: [],
      clienteleList: [],
      supplierList: []
    }
  },
  mounted () {
    this.selectMaterielList()
    this.selectClienteleList()
  },
  methods: {
    materielChange (value) {
      if (value) {
        this.materielList.forEach(item => {
          if (item.id === value) {
            this.form.setFieldsValue({
              materielCode: item.code,
              materielName: item.name,
              model: item.model,
              unit: item.unit
            })
          }
        })
      }
    },
    clienteleChange (value) {
      if (value) {
        this.clienteleList.forEach(item => {
          if (item.id === value) {
            this.form.setFieldsValue({
              clienteleName: item.name,
              clienteleCode: item.code
            })
          }
        })
      }
    },
    selectMaterielList () {
      this.$get(`/cos/materiel-info/list`).then((r) => {
        this.materielList = r.data.data
      })
    },
    selectClienteleList () {
      this.$get(`/cos/clientele-info/list`).then((r) => {
        this.clienteleList = r.data.data
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
          values.shipDate = moment(values.shipDate).format('YYYY-MM-DD')
          this.loading = true
          this.$post('/cos/ship-check-info', {
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
