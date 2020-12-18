import Vue from 'vue'
import Router from 'vue-router'
import Information from "../components/Information";
import Settings from "../components/Settings";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/information',
      component: Information
    },
    {
      path: '/settings',
      component: Settings
    }
  ]
})
