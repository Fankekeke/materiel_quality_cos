<template>
  <a-modal v-model="show" title="新增物料信息" @cancel="onClose" :width="800">
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
        <a-col :span="12">
          <a-form-item label='物料名称' v-bind="formItemLayout">
            <a-input v-decorator="[
            'name',
            { rules: [{ required: true, message: '请输入物料名称!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='单位' v-bind="formItemLayout">
            <a-input v-decorator="[
            'unit',
            { rules: [{ required: true, message: '请输入单位!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='规格型号' v-bind="formItemLayout">
            <a-input v-decorator="[
            'model',
            { rules: [{ required: true, message: '请输入规格型号!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='物料类型' v-bind="formItemLayout">
            <a-select v-decorator="[
              'materielType',
              { rules: [{ required: true, message: '请输入物料类型!' }] }
              ]">
              <a-select-option value="0">物料</a-select-option>
              <a-select-option value="1">产品</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='所属分类' v-bind="formItemLayout">
            <a-select v-decorator="[
              'materielSort',
              { rules: [{ required: true, message: '请输入所属分类!' }] }
              ]">
              <a-select-option value="1">发动机</a-select-option>
              <a-select-option value="2">PVC展板</a-select-option>
              <a-select-option value="3">灯片</a-select-option>
              <a-select-option value="4">原材料</a-select-option>
              <a-select-option value="5">外协件物料</a-select-option>
              <a-select-option value="6">自制件物料</a-select-option>
              <a-select-option value="7">商品</a-select-option>
              <a-select-option value="8">资产类物料</a-select-option>
              <a-select-option value="9">服务类物料</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='检测模板' v-bind="formItemLayout">
            <a-select v-decorator="[
              'templateId',
              { rules: [{ required: true, message: '请输入检测模板!' }] }
              ]">
              <a-select-option :value="item.id" v-for="(item, index) in templateList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='检测项' v-bind="formItemLayout">
            <a-select mode="multiple" v-decorator="[
              'detectionIds',
              { rules: [{ required: true, message: '请输入检测项!' }] }
              ]">
              <a-select-option :value="item.id" v-for="(item, index) in detectionList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='照片' v-bind="formItemLayout">
            <a-upload
              name="avatar"
              action="http://127.0.0.1:9527/file/fileUpload/"
              list-type="picture-card"
              :file-list="fileList"
              @preview="handlePreview"
              @change="picHandleChange"
            >
              <div v-if="fileList.length < 2">
                <a-icon type="plus" />
                <div class="ant-upload-text">
                  Upload
                </div>
              </div>
            </a-upload>
            <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
              <img alt="example" style="width: 100%" :src="previewImage" />
            </a-modal>
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
  name: 'materielAdd',
  props: {
    materielAddVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.materielAddVisiable
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
      materielTypeList: [],
      templateList: [],
      detectionList: []
    }
  },
  mounted () {
    this.selectTemplateList()
    this.selectDetectionList()
  },
  methods: {
    selectTemplateList () {
      this.$get(`/cos/detection-template-info/list`).then((r) => {
        this.templateList = r.data.data
      })
    },
    selectDetectionList () {
      this.$get(`/cos/detection-info/list`).then((r) => {
        this.detectionList = r.data.data
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
          this.$post('/cos/materiel-info', {
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
