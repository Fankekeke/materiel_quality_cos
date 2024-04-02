<template>
  <a-modal v-model="show" title="出货检测详情" @cancel="onClose" :width="950">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
        关闭
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
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">检验结果</span></a-col>
        <a-col :span="8"><b>检测数量：</b>
          {{ shipData.checkNum }}
        </a-col>
        <a-col :span="8"><b>不合格数量：</b>
          {{ shipData.unqualifiedNum ? shipData.unqualifiedNum : '- -' }}
        </a-col>
        <a-col :span="8"><b>检测日期：</b>
          {{ shipData.checkDate ? shipData.checkDate : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>检测结果：</b>
          <span v-if="shipData.checkStatus == 1">通过</span>
          <span v-if="shipData.checkStatus == 2">不通过</span>
        </a-col>
        <a-col :span="8"><b>备注：</b>
          {{ shipData.content ? shipData.content : '- -' }}
        </a-col>
        <a-col :span="8"><b>检测人员：</b>
          {{ shipData.staffName ? shipData.staffName : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>致命缺陷率：</b>
          {{ shipData.fatalDefectRate }}
        </a-col>
        <a-col :span="8"><b>严重缺陷率：</b>
          {{ shipData.criticalDefectRate ? shipData.criticalDefectRate : '- -' }}
        </a-col>
        <a-col :span="8"><b>轻微缺陷率：</b>
          {{ shipData.minorDefectRate ? shipData.minorDefectRate : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>致命缺陷数量：</b>
          {{ shipData.fatalDefectNum }}
        </a-col>
        <a-col :span="8"><b>严重缺陷数量：</b>
          {{ shipData.criticalDefectNum ? shipData.criticalDefectNum : '- -' }}
        </a-col>
        <a-col :span="8"><b>轻微缺陷数量：</b>
          {{ shipData.minorDefectNum ? shipData.minorDefectNum : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <br/>
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
  name: 'shipView',
  props: {
    shipShow: {
      type: Boolean,
      default: false
    },
    shipData: {
      type: Object
    }
  },
  computed: {
    show: {
      get: function () {
        return this.shipShow
      },
      set: function () {
      }
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
      userInfo: null,
      shipInfo: null,
      shopInfo: null,
      brandInfo: null,
      typeInfo: null
    }
  },
  watch: {
    shipShow: function (value) {
      if (value) {
        this.imagesInit(this.shipData.images)
      }
    }
  },
  methods: {
    local (shipData) {
      baiduMap.clearOverlays()
      baiduMap.rMap().enableScrollWheelZoom(true)
      // eslint-disable-next-line no-undef
      let point = new BMap.Point(shipData.longitude, shipData.latitude)
      baiduMap.pointAdd(point)
      baiduMap.findPoint(point, 16)
      // let driving = new BMap.DrivingRoute(baiduMap.rMap(), {renderOptions:{map: baiduMap.rMap(), autoViewport: true}});
      // driving.search(new BMap.Point(this.nowPoint.lng,this.nowPoint.lat), new BMap.Point(scenic.point.split(",")[0],scenic.point.split(",")[1]));
    },
    dataInit (shipNo) {
      this.$get(`/cos/ship-info/detail/${shipNo}`).then((r) => {
        this.shipInfo = r.data.ship
        this.shopInfo = r.data.shop
        this.brandInfo = r.data.brand
        this.typeInfo = r.data.type
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
