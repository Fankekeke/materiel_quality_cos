<template>
  <a-modal v-model="show" title="来料检测详情" @cancel="onClose" :width="950">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
        关闭
      </a-button>
    </template>
    <div style="font-size: 13px;font-family: SimHei" v-if="incomeData !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">检验单信息</span></a-col>
        <a-col :span="8"><b>检验单编号：</b>
          {{ incomeData.code }}
        </a-col>
        <a-col :span="8"><b>检验单名称：</b>
          {{ incomeData.name ? incomeData.name : '- -' }}
        </a-col>
        <a-col :span="8"><b>产品物料编码：</b>
          {{ incomeData.materielCode ? incomeData.materielCode : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>产品物料名称：</b>
          {{ incomeData.materielName }}
        </a-col>
        <a-col :span="8"><b>单位：</b>
          {{ incomeData.unit }}
        </a-col>
        <a-col :span="8"><b>供应商编码：</b>
          {{ incomeData.supplierName }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>供应商简称：</b>
          {{ incomeData.abbreviation }}
        </a-col>
        <a-col :span="8"><b>供应商批次号：</b>
          {{ incomeData.batchNumber }}
        </a-col>
        <a-col :span="8"><b>本次接收数量：</b>
          {{ incomeData.receiveQuantity }}
        </a-col>
      </a-row>
      <br/>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">结果统计</span></a-col>
        <a-col :span="8"><b>本次检测数量：</b>
          {{ incomeData.checkQuantity }}
        </a-col>
        <a-col :span="8"><b>不合格数量：</b>
          {{ incomeData.unqualifiedQuantity ? incomeData.unqualifiedQuantity : '- -' }}
        </a-col>
        <a-col :span="8"><b>检测日期：</b>
          {{ incomeData.checkDate ? incomeData.checkDate : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>检测结果：</b>
          <span v-if="incomeData.checkStatus == 1">通过</span>
          <span v-if="incomeData.checkStatus == 2">不通过</span>
        </a-col>
        <a-col :span="16"><b>备注：</b>
          {{ incomeData.content ? incomeData.content : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>致命缺陷率：</b>
          {{ incomeData.fatalDefectRate }}
        </a-col>
        <a-col :span="8"><b>严重缺陷率：</b>
          {{ incomeData.criticalDefectRate ? incomeData.criticalDefectRate : '- -' }}
        </a-col>
        <a-col :span="8"><b>轻微缺陷率：</b>
          {{ incomeData.minorDefectRate ? incomeData.minorDefectRate : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>致命缺陷数量：</b>
          {{ incomeData.fatalDefectNum }}
        </a-col>
        <a-col :span="8"><b>严重缺陷数量：</b>
          {{ incomeData.criticalDefectNum ? incomeData.criticalDefectNum : '- -' }}
        </a-col>
        <a-col :span="8"><b>轻微缺陷数量：</b>
          {{ incomeData.minorDefectNum ? incomeData.minorDefectNum : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>供应商名称：</b>
          {{ incomeData.supplierName }}
        </a-col>
        <a-col :span="8"><b>检测人员：</b>
          {{ incomeData.staffName ? incomeData.staffName : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">检测项</span></a-col>
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
    </div>
    <br/>
  </a-modal>
</template>

<script>
import baiduMap from '@/utils/map/baiduMap'
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
export default {
  name: 'incomeView',
  props: {
    incomeShow: {
      type: Boolean,
      default: false
    },
    incomeData: {
      type: Object
    }
  },
  computed: {
    show: {
      get: function () {
        return this.incomeShow
      },
      set: function () {
      }
    },
    columns () {
      return [{
        title: '检测项类型',
        dataIndex: 'typeName'
      }, {
        title: '检测工具',
        dataIndex: 'checkUtil'
      }, {
        title: '检测要求',
        dataIndex: 'checkRequire'
      }, {
        title: '标准值',
        dataIndex: 'standardValue'
      }, {
        title: '单位',
        dataIndex: 'unit'
      }, {
        title: '误差上限',
        dataIndex: 'errorMax'
      }, {
        title: '误差下限',
        dataIndex: 'errorMin'
      }, {
        title: '备注',
        dataIndex: 'content'
      }]
    }
  },
  data () {
    return {
      loading: false,
      fileList: [],
      previewVisible: false,
      previewImage: '',
      repairInfo: null,
      reserveInfo: null,
      durgList: [],
      logisticsList: [],
      checkItem: [],
      userInfo: null,
      incomeInfo: null,
      shopInfo: null,
      brandInfo: null,
      typeInfo: null
    }
  },
  watch: {
    incomeShow: function (value) {
      if (value) {
        this.dataInit(this.incomeData.code)
      }
    }
  },
  methods: {
    local (incomeData) {
      baiduMap.clearOverlays()
      baiduMap.rMap().enableScrollWheelZoom(true)
      // eslint-disable-next-line no-undef
      let point = new BMap.Point(incomeData.longitude, incomeData.latitude)
      baiduMap.pointAdd(point)
      baiduMap.findPoint(point, 16)
      // let driving = new BMap.DrivingRoute(baiduMap.rMap(), {renderOptions:{map: baiduMap.rMap(), autoViewport: true}});
      // driving.search(new BMap.Point(this.nowPoint.lng,this.nowPoint.lat), new BMap.Point(scenic.point.split(",")[0],scenic.point.split(",")[1]));
    },
    dataInit (code) {
      this.$get(`/cos/detection-chcek-info/byCode`, {code}).then((r) => {
        this.checkItem = r.data.data
      })
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
    onClose () {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>

</style>
