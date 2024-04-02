<template>
  <a-modal v-model="show" title="客户详情" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
        关闭
      </a-button>
    </template>
    <div style="font-size: 13px;font-family: SimHei" v-if="clienteleData !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">客户信息</span></a-col>
        <a-col :span="8"><b>客户编号：</b>
          {{ clienteleData.code }}
        </a-col>
        <a-col :span="8"><b>客户名称：</b>
          {{ clienteleData.name ? clienteleData.name : '- -' }}
        </a-col>
        <a-col :span="8"><b>客户简称：</b>
          {{ clienteleData.abbreviation ? clienteleData.abbreviation : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>联系人：</b>
          {{ clienteleData.contact }}
        </a-col>
        <a-col :span="8"><b>联系人电话：</b>
          {{ clienteleData.contactPhone }}
        </a-col>
        <a-col :span="8"><b>创建时间：</b>
          {{ clienteleData.createDate }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>客户电话：</b>
          {{ clienteleData.phone }}
        </a-col>
        <a-col :span="8"><b>类型：</b>
          <span v-if="clienteleData.type == 1">一级客户</span>
          <span v-if="clienteleData.type == 2">二级客户</span>
          <span v-if="clienteleData.type == 3">三级客户</span>
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
  name: 'clienteleView',
  props: {
    clienteleShow: {
      type: Boolean,
      default: false
    },
    clienteleData: {
      type: Object
    }
  },
  computed: {
    show: {
      get: function () {
        return this.clienteleShow
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
      clienteleInfo: null,
      shopInfo: null,
      brandInfo: null,
      typeInfo: null
    }
  },
  watch: {
    clienteleShow: function (value) {
      if (value) {
        this.imagesInit(this.clienteleData.images)
      }
    }
  },
  methods: {
    local (clienteleData) {
      baiduMap.clearOverlays()
      baiduMap.rMap().enableScrollWheelZoom(true)
      // eslint-disable-next-line no-undef
      let point = new BMap.Point(clienteleData.longitude, clienteleData.latitude)
      baiduMap.pointAdd(point)
      baiduMap.findPoint(point, 16)
      // let driving = new BMap.DrivingRoute(baiduMap.rMap(), {renderOptions:{map: baiduMap.rMap(), autoViewport: true}});
      // driving.search(new BMap.Point(this.nowPoint.lng,this.nowPoint.lat), new BMap.Point(scenic.point.split(",")[0],scenic.point.split(",")[1]));
    },
    dataInit (clienteleNo) {
      this.$get(`/cos/clientele-info/detail/${clienteleNo}`).then((r) => {
        this.clienteleInfo = r.data.clientele
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
