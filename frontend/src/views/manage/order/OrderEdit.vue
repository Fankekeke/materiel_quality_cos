<template>
  <a-modal v-model="show" title="修改工单信息" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
        修改
      </a-button>
    </template>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="12">
          <a-form-item label='工单名称' v-bind="formItemLayout">
            <a-input v-decorator="[
            'name',
            { rules: [{ required: true, message: '请输入工单名称!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='工单来源' v-bind="formItemLayout">
            <a-input v-decorator="[
            'orderSource',
            { rules: [{ required: true, message: '请输入工单来源!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='工单数量' v-bind="formItemLayout">
            <a-input-number :min="1" :max="100" v-decorator="[
            'orderNum',
            { rules: [{ required: true, message: '请输入工单数量!' }] }
            ]" style="width: 100%"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='选择物料' v-bind="formItemLayout">
            <a-select v-decorator="[
              'materielId',
              { rules: [{ required: true, message: '请输入物料!' }] }
              ]">
              <a-select-option :value="item.id" v-for="(item, index) in materielList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='选择客户' v-bind="formItemLayout">
            <a-select v-decorator="[
              'clienteleId',
              { rules: [{ required: true, message: '请输入所属客户!' }] }
              ]">
              <a-select-option :value="item.id" v-for="(item, index) in clienteleList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
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
  name: 'orderEdit',
  props: {
    orderEditVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.orderEditVisiable
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      rowId: null,
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      formLoading: false,
      fileList: [],
      previewVisible: false,
      previewImage: '',
      shopList: [],
      orderTypeList: [],
      brandList: [],
      materielList: [],
      clienteleList: []
    }
  },
  mounted () {
    this.selectClienteleList()
    this.selectMaterielList()
  },
  methods: {
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
    imagesInit (images) {
      if (images !== null && images !== '') {
        let imageList = []
        images.split(',').forEach((image, index) => {
          imageList.push({uid: index, name: image, status: 'done', url: 'http://127.0.0.1:9527/imagesWeb/' + image})
        })
        this.fileList = imageList
      }
    },
    responsibleInit (responsible) {
      this.formLoading = false
      if (responsible !== null && responsible !== '') {
        let responsibleList = []
        responsible.split(',').forEach((id, index) => {
          responsibleList.push(parseInt(id))
        })
        this.checkedList = responsibleList
        console.log(JSON.stringify(this.checkedList))
        this.onChange(this.checkedList)
      }
      setTimeout(() => {
        this.formLoading = true
      }, 200)
    },
    setFormValues ({...order}) {
      this.rowId = order.id
      let fields = ['name', 'orderSource', 'orderNum', 'materielId', 'clienteleId']
      let obj = {}
      Object.keys(order).forEach((key) => {
        if (key === 'images') {
          this.fileList = []
          this.imagesInit(order['images'])
        }
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          obj[key] = order[key]
        }
        if (key === 'materielId' || key === 'clienteleId') {
          order[key] = order[key].toString()
        }
      })
      setTimeout(() => {
        this.form.setFieldsValue(obj)
      }, 300)
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
      // 获取图片List
      let images = []
      this.fileList.forEach(image => {
        if (image.response !== undefined) {
          images.push(image.response)
        } else {
          images.push(image.name)
        }
      })
      this.form.validateFields((err, values) => {
        values.id = this.rowId
        values.images = images.length > 0 ? images.join(',') : null
        if (!err) {
          if (values.factoryDate) {
            values.factoryDate = moment(values.factoryDate).format('YYYY-MM-DD')
          }
          this.loading = true
          this.$put('/cos/order-info', {
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
