<template>
  <div :class="[multipage === true ? 'multi-page':'single-page', 'not-menu-page', 'home-page']">
    <dv-border-box-11 title="欢迎各位领导莅临指导" style="height: 80vh">
      <a-row style="padding: 50px;padding-top: 80px">
        <a-col :span="12">
          <div class="left-chart-1">
            <div class="lc1-details" style="color: white">设备运行总数<span>430</span></div>
            <dv-capsule-chart class="lc1-chart" :config="config" />
            <dv-decoration-2 style="height:10px;" />
          </div>
        </a-col>
        <a-col :span="12">
          <div class="right-chart-1">
            <div class="rc1-chart-container">
              <div class="left" style="color: white">
                <div>设备运行总数 262</div>
              </div>
              <dv-capsule-chart class="right" :config="config2" />
            </div>
          </div>
        </a-col>
      </a-row>
    </dv-border-box-11>
  </div>
</template>
<script>
import HeadInfo from '@/views/common/HeadInfo'
import {mapState} from 'vuex'
import moment from 'moment'
import Home from './manage/component/home/Home'
import Work from './manage/component/work/Work'
moment.locale('zh-cn')

export default {
  name: 'HomePage',
  components: {Home, Work, HeadInfo},
  data () {
    return {
      config: {
        data: [
          {
            name: '收费系统',
            value: 167
          },
          {
            name: '通信系统',
            value: 67
          },
          {
            name: '监控系统',
            value: 123
          },
          {
            name: '供配电系统',
            value: 55
          },
          {
            name: '其他',
            value: 98
          }
        ],
        colors: ['#00baff', '#3de7c9', '#fff', '#ffc530', '#469f4b'],
        unit: '件'
      },
      config2: {
        data: [
          {
            name: '收费系统',
            value: 25
          },
          {
            name: '通信系统',
            value: 66
          },
          {
            name: '监控系统',
            value: 123
          },
          {
            name: '供配电系统',
            value: 72
          },
          {
            name: '其他',
            value: 99
          }
        ],
        unit: '件'
      },
      titleData: {
        staffNum: 0,
        totalRevenue: 0,
        totalOrderNum: 0,
        roomNum: 0
      },
      series: [],
      chartOptions: {
        chart: {
          toolbar: {
            show: false
          }
        },
        plotOptions: {
          bar: {
            horizontal: false,
            columnWidth: '35%'
          }
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          show: true,
          width: 2,
          colors: ['transparent']
        },
        xaxis: {
          categories: []
        },
        fill: {
          opacity: 1

        }
      },
      todayIp: '',
      todayVisitCount: '',
      totalVisitCount: '',
      userRole: '',
      userDept: '',
      lastLoginTime: '',
      welcomeMessage: '',
      bulletinList: []
    }
  },
  computed: {
    ...mapState({
      multipage: state => state.setting.multipage,
      user: state => state.account.user
    }),
    avatar () {
      return `static/avatar/${this.user.avatar}`
    }
  },
  methods: {
    welcome () {
      const date = new Date()
      const hour = date.getHours()
      let time = hour < 6 ? '早上好' : (hour <= 11 ? '上午好' : (hour <= 13 ? '中午好' : (hour <= 18 ? '下午好' : '晚上好')))
      return `${time}，${this.user.username}`
    },
    setTitleData (titleData) {
      this.titleData = titleData
    }
  },
  mounted () {
    // this.welcomeMessage = this.welcome()
    // this.$get(`index/${this.user.username}`).then((r) => {
    //   let data = r.data.data
    //   this.todayIp = data.todayIp
    //   this.todayVisitCount = data.todayVisitCount
    //   this.totalVisitCount = data.totalVisitCount
    //   let sevenVisitCount = []
    //   let dateArr = []
    //   for (let i = 6; i >= 0; i--) {
    //     let time = moment().subtract(i, 'days').format('MM-DD')
    //     let contain = false
    //     for (let o of data.lastSevenVisitCount) {
    //       if (o.days === time) {
    //         contain = true
    //         sevenVisitCount.push(o.count)
    //       }
    //     }
    //     if (!contain) {
    //       sevenVisitCount.push(0)
    //     }
    //     dateArr.push(time)
    //   }
    //   let sevenUserVistCount = []
    //   for (let i = 6; i >= 0; i--) {
    //     let time = moment().subtract(i, 'days').format('MM-DD')
    //     let contain = false
    //     for (let o of data.lastSevenUserVisitCount) {
    //       if (o.days === time) {
    //         contain = true
    //         sevenUserVistCount.push(o.count)
    //       }
    //     }
    //     if (!contain) {
    //       sevenUserVistCount.push(0)
    //     }
    //   }
    //   this.$refs.count.updateSeries([
    //     {
    //       name: '您',
    //       data: sevenUserVistCount
    //     },
    //     {
    //       name: '总数',
    //       data: sevenVisitCount
    //     }
    //   ], true)
    //   this.$refs.count.updateOptions({
    //     xaxis: {
    //       categories: dateArr
    //     },
    //     title: {
    //       text: '近七日系统访问记录',
    //       align: 'left'
    //     }
    //   }, true, true)
    // }).catch((r) => {
    //   console.error(r)
    //   this.$message.error('获取首页信息失败')
    // })
  }
}
</script>
<style lang="less">
  .home-page {
    .head-info {
      margin-bottom: .5rem;
      .head-info-card {
        padding: .5rem;
        border-color: #f1f1f1;
        .head-info-avatar {
          display: inline-block;
          float: left;
          margin-right: 1rem;
          img {
            width: 5rem;
            border-radius: 2px;
          }
        }
        .head-info-count {
          display: inline-block;
          float: left;
          .head-info-welcome {
            font-size: 1.05rem;
            margin-bottom: .1rem;
          }
          .head-info-desc {
            color: rgba(0, 0, 0, 0.45);
            font-size: .8rem;
            padding: .2rem 0;
            p {
              margin-bottom: 0;
            }
          }
          .head-info-time {
            color: rgba(0, 0, 0, 0.45);
            font-size: .8rem;
            padding: .2rem 0;
          }
        }
      }
    }
    .count-info {
      .visit-count-wrapper {
        padding-left: 0 !important;
        .visit-count {
          padding: .5rem;
          border-color: #f1f1f1;
          .ant-card-body {
            padding: .5rem 1rem !important;
          }
        }
      }
      .project-wrapper {
        padding-right: 0 !important;
        .project-card {
          border: none !important;
          .ant-card-head {
            border-left: 1px solid #f1f1f1 !important;
            border-top: 1px solid #f1f1f1 !important;
            border-right: 1px solid #f1f1f1 !important;
          }
          .ant-card-body {
            padding: 0 !important;
            table {
              width: 100%;
              td {
                width: 50%;
                border: 1px solid #f1f1f1;
                padding: .6rem;
                .project-avatar-wrapper {
                  display:inline-block;
                  float:left;
                  margin-right:.7rem;
                  .project-avatar {
                    color: #42b983;
                    background-color: #d6f8b8;
                  }
                }
              }
            }
          }
          .project-detail {
            display:inline-block;
            float:left;
            text-align:left;
            width: 78%;
            .project-name {
              font-size:.9rem;
              margin-top:-2px;
              font-weight:600;
            }
            .project-desc {
              color:rgba(0, 0, 0, 0.45);
              p {
                margin-bottom:0;
                font-size:.6rem;
                white-space:normal;
              }
            }
          }
        }
      }
    }
  }
</style>
