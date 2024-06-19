<template>
  <a-modal v-model="show" title="填报出货检测信息" @cancel="onClose" :width="950">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
        修改
      </a-button>
    </template>
    <div style="font-size: 13px;font-family: SimHei" v-if="shipData !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">检验单信息</span></a-col>
        <a-col :span="8"><b>检验单编号：</b>
          {{ shipData.code }}
        </a-col>
        <a-col :span="8"><b>检验单名称：</b>
          {{ shipData.name ? shipData.name : '- -' }}
        </a-col>
        <a-col :span="8"><b>产品物料编码：</b>
          {{ shipData.materielCode ? shipData.materielCode : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>产品物料名称：</b>
          {{ shipData.materielName }}
        </a-col>
        <a-col :span="8"><b>单位：</b>
          {{ shipData.unit }}
        </a-col>
        <a-col :span="8"><b>规格型号：</b>
          {{ shipData.model }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>发货数量：</b>
          {{ shipData.shipNum }}
        </a-col>
        <a-col :span="8"><b>客户名称：</b>
          {{ shipData.clienteleName }}
        </a-col>
        <a-col :span="8"><b>批次号：</b>
          {{ shipData.batchNumber }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>出货日期：</b>
          {{ shipData.shipDate }}
        </a-col>
      </a-row>
      <br/>
      <br/>
    </div>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-divider orientation="left">
          <span style="font-size: 13px;font-family: SimHei">结果统计</span>
        </a-divider>
        <a-col :span="8">
          <a-form-item label='检测数量' disabled v-bind="formItemLayout">
            <a-input-number :min="0" :max="99999" v-decorator="[
            'checkNum',
            { rules: [{ required: true, message: '请输入本次检测数量!' }] }
            ]" style="width: 100%"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='不合格数量' disabled v-bind="formItemLayout">
            <a-input-number :min="0" :max="99999" v-decorator="[
            'unqualifiedNum',
            { rules: [{ required: true, message: '请输入不合格数量!' }] }
            ]" style="width: 100%"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='检测日期' disabled v-bind="formItemLayout">
            <a-date-picker style="width: 100%;" v-decorator="[
            'checkDate',
            { rules: [{ required: true, message: '请输入检测日期!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='检测结果' disabled v-bind="formItemLayout">
            <a-select v-decorator="[
              'checkStatus',
              { rules: [{ required: true, message: '请输入客户类型!' }] }
              ]">
              <a-select-option value="1">通过</a-select-option>
              <a-select-option value="2">不通过</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='检测人员' v-bind="formItemLayout">
            <a-select v-decorator="[
              'staffId',
              { rules: [{ required: true, message: '请输入检测人员!' }] }
              ]">
              <a-select-option :value="item.id" v-for="(item, index) in staffList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-divider orientation="left">
          <span style="font-size: 13px;font-family: SimHei">检验统计</span>
        </a-divider>
        <a-col :span="8">
          <a-form-item label='致命缺陷率' v-bind="formItemLayout">
            <a-input-number :min="0" :max="99999" v-decorator="[
            'fatalDefectRate',
            { rules: [{ required: true, message: '请输入致命缺陷率!' }] }
            ]" style="width: 100%"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='严重缺陷率' v-bind="formItemLayout">
            <a-input-number :min="0" :max="99999" v-decorator="[
            'criticalDefectRate',
            { rules: [{ required: true, message: '请输入严重缺陷率!' }] }
            ]" style="width: 100%"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='轻微缺陷率' v-bind="formItemLayout">
            <a-input-number :min="0" :max="99999" v-decorator="[
            'minorDefectRate',
            { rules: [{ required: true, message: '请输入轻微缺陷率!' }] }
            ]" style="width: 100%"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='致命缺陷数量' v-bind="formItemLayout">
            <a-input-number :min="0" :max="99999" v-decorator="[
            'fatalDefectNum',
            { rules: [{ required: true, message: '请输入致命缺陷数量!' }] }
            ]" style="width: 100%"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='严重缺陷数量' v-bind="formItemLayout">
            <a-input-number :min="0" :max="99999" v-decorator="[
            'criticalDefectNum',
            { rules: [{ required: true, message: '请输入严重缺陷数量!' }] }
            ]" style="width: 100%"/>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label='轻微缺陷数量' v-bind="formItemLayout">
            <a-input-number :min="0" :max="99999" v-decorator="[
            'minorDefectNum',
            { rules: [{ required: true, message: '请输入轻微缺陷数量!' }] }
            ]" style="width: 100%"/>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='备注' v-bind="formItemLayout">
            <a-textarea :rows="4" v-decorator="[
            'content'
            ]"/>
          </a-form-item>
        </a-col>
        <a-divider orientation="left">
          <span style="font-size: 13px;font-family: SimHei">检验项</span>
        </a-divider>
        <a-col :span="24">
          <a-table :columns="columns" :data-source="checkItem">
            <template slot="typeNameField" slot-scope="text, record">
              <a-input v-model="record.typeName"/>
            </template>
            <template slot="checkUtilField" slot-scope="text, record">
              <a-input v-model="record.checkUtil"/>
            </template>
            <template slot="checkRequireField" slot-scope="text, record">
              <a-input v-model="record.checkRequire"/>
            </template>
            <template slot="standardValueField" slot-scope="text, record">
              <a-input v-model="record.standardValue"/>
            </template>
            <template slot="unitField" slot-scope="text, record">
              <a-input v-model="record.unit"/>
            </template>
            <template slot="errorMaxField" slot-scope="text, record">
              <a-input-number :min="0" :max="99999" v-model="record.errorMax"/>
            </template>
            <template slot="errorMinField" slot-scope="text, record">
              <a-input-number :min="-9999" :max="99999" v-model="record.errorMin"/>
            </template>
            <template slot="contentField" slot-scope="text, record">
              <a-input v-model="record.content"/>
            </template>
          </a-table>
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
  name: 'shipEdit',
  props: {
    shipEditVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.shipEditVisiable
      },
      set: function () {
      }
    },
    columns () {
      return [{
        title: '检测项类型',
        dataIndex: 'typeName',
        scopedSlots: {customRender: 'typeNameField'}
      }, {
        title: '检测工具',
        dataIndex: 'checkUtil',
        scopedSlots: {customRender: 'checkUtilField'}
      }, {
        title: '检测要求',
        dataIndex: 'checkRequire',
        scopedSlots: {customRender: 'checkRequireField'}
      }, {
        title: '标准值',
        dataIndex: 'standardValue',
        scopedSlots: {customRender: 'standardValueField'}
      }, {
        title: '单位',
        dataIndex: 'unit',
        scopedSlots: {customRender: 'unitField'}
      }, {
        title: '误差上限',
        dataIndex: 'errorMax',
        scopedSlots: {customRender: 'errorMaxField'}
      }, {
        title: '误差下限',
        dataIndex: 'errorMin',
        scopedSlots: {customRender: 'errorMinField'}
      }, {
        title: '备注',
        dataIndex: 'content',
        scopedSlots: {customRender: 'contentField'}
      }]
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
      shipData: null,
      shopList: [],
      shipTypeList: [],
      staffList: [],
      checkItem: []
    }
  },
  mounted () {
    this.selectStaffList()
  },
  methods: {
    selectCheckItem (materielId) {
      this.$get(`/cos/materiel-info/checkItem`, {id: materielId}).then((r) => {
        r.data.data.forEach(item => {
          // 1.尺寸 2.外观 3.重量 4.性能 5.成分
          if (item.detectionItem === '1') {
            item.typeName = '尺寸'
          } else if (item.detectionItem === '2') {
            item.typeName = '外观'
          } else if (item.detectionItem === '3') {
            item.typeName = '重量'
          } else if (item.detectionItem === '4') {
            item.typeName = '性能'
          } else if (item.detectionItem === '5') {
            item.typeName = '成分'
          }
          item.content = ''
          item.checkRequire = '/'
          item.standardValue = '100'
          item.unit = '个'
        })
        this.checkItem = r.data.data
      })
    },
    selectStaffList () {
      this.$get(`/cos/staff-info/list`).then((r) => {
        this.staffList = r.data.data
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
    setFormValues ({...ship}) {
      this.shipData = ship
      this.rowId = ship.id
      this.selectCheckItem(ship.materielId)
      // let fields = ['name', 'phone', 'remark', 'score', 'shipLevel', 'abbreviation']
      // let obj = {}
      // Object.keys(ship).forEach((key) => {
      //   if (key === 'images') {
      //     this.fileList = []
      //     this.imagesInit(ship['images'])
      //   }
      //   if (fields.indexOf(key) !== -1) {
      //     this.form.getFieldDecorator(key)
      //     obj[key] = ship[key]
      //   }
      // })
      // this.form.setFieldsValue(obj)
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
          if (values.checkDate) {
            values.checkDate = moment(values.checkDate).format('YYYY-MM-DD')
          }
          values.detectionCheck = JSON.stringify(this.checkItem)
          this.loading = true
          this.$post('/cos/ship-check-info/additional', {
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
