import Vue from 'vue'
import Router from 'vue-router'
import Information from "../components/Information"
import Settings from "../components/Settings"
import RealTimeGraph from "../components/RealTimeGraph";
import RealTimeStatus from "../components/RealTimeStatus";
import HistoryGraph from "../components/HistoryGraph";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/information'
    },
    {
      path:'/information',
      component: Information
    },
    {
      path: '/settings',
      component: Settings
    },
    {
      path: '/realTimeStatus',
      component: RealTimeStatus
    },
    {
      path: '/realTimeGraph',
      component: RealTimeGraph
    },
    {
      path: '/historyGraph',
      component: HistoryGraph
    }
  ]
})
