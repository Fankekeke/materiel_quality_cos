<template>
  <a-modal v-model="show" title="过程检测详情" @cancel="onClose" :width="950">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
        关闭
      </a-button>
    </template>
    <div style="font-size: 13px;font-family: SimHei" v-if="processData !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">检验单信息</span></a-col>
        <a-col :span="8"><b>检验单编号：</b>
          {{ processData.code }}
        </a-col>
        <a-col :span="8"><b>检验单名称：</b>
          {{ processData.name ? processData.name : '- -' }}
        </a-col>
        <a-col :span="8"><b>检验类型：</b>
          <span v-if="processData.checkType == 1">首检</span>
          <span v-if="processData.checkType == 2">末检</span>
          <span v-if="processData.checkType == 3">自检</span>
          <span v-if="processData.checkType == 4">巡检</span>
          <span v-if="processData.checkType == 5">点检</span>
          <span v-if="processData.checkType == 6">终检</span>
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>工单编码：</b>
          {{ processData.orderCode }}
        </a-col>
        <a-col :span="8"><b>工单名称：</b>
          {{ processData.orderName }}
        </a-col>
        <a-col :span="8"><b>工作站编号：</b>
          {{ processData.stationCode }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>工作站名称：</b>
          {{ processData.stationName }}
        </a-col>
        <a-col :span="8"><b>检测数量：</b>
          {{ processData.checkNum }}
        </a-col>
        <a-col :span="8"><b>产品编码：</b>
          {{ processData.materielCode }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>产品名称：</b>
          {{ processData.materielName }}
        </a-col>
        <a-col :span="8"><b>单位：</b>
          {{ processData.unit }}
        </a-col>
        <a-col :span="8"><b>规格型号：</b>
          {{ processData.model }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>工序编码：</b>
          {{ processData.processCode }}
        </a-col>
        <a-col :span="8"><b>工序名称：</b>
          {{ processData.processName }}
        </a-col>
        <a-col :span="8"><b>任务编号：</b>
          {{ processData.taskCode }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>任务名称：</b>
          {{ processData.taskName }}
        </a-col>
      </a-row>
      <br/>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">检验结果</span></a-col>
        <a-col :span="8"><b>不合格数：</b>
          {{ processData.unqualifiedNum }}
        </a-col>
        <a-col :span="8"><b>合格品数量：</b>
          {{ processData.qualifiedNum ? processData.qualifiedNum : '- -' }}
        </a-col>
        <a-col :span="8"><b>检测日期：</b>
          {{ processData.checkDate ? processData.checkDate : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>检测结果：</b>
          <span v-if="processData.checkStatus == 1">通过</span>
          <span v-if="processData.checkStatus == 2">不通过</span>
        </a-col>
        <a-col :span="8"><b>检测人员：</b>
          {{ processData.staffName ? processData.staffName : '- -' }}
        </a-col>
        <a-col :span="8"><b>备注：</b>
          {{ processData.content ? processData.content : '- -' }}
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
  name: 'processView',
  props: {
    processShow: {
      type: Boolean,
      default: false
    },
    processData: {
      type: Object
    }
  },
  computed: {
    show: {
      get: function () {
        return this.processShow
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
      checkItem: [],
      userInfo: null,
      processInfo: null,
      shopInfo: null,
      brandInfo: null,
      typeInfo: null
    }
  },
  watch: {
    show: function (value) {
      if (value) {
        // this.imagesInit(this.processData.images)
        this.dataInit(this.processData.code)
      }
    }
  },
  methods: {
    local (processData) {
      baiduMap.clearOverlays()
      baiduMap.rMap().enableScrollWheelZoom(true)
      // eslint-disable-next-line no-undef
      let point = new BMap.Point(processData.longitude, processData.latitude)
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
