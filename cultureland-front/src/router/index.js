import Vue from 'vue'
import VueRouter from 'vue-router'
// import { useRoute } from 'vue-router/types/composables'
import MainView from '../views/MainView.vue'
import UserView from '../views/UserView.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    name: "home",
    component: MainView,
    children :[
      {
        path: "/aptlist",
        name: "aptlist",
        component:()=>import("@/components/place/AptInfoList"),
      },
      {
        path: "/aptdetail",
        name: "aptdetail",
        component:()=>import("@/components/place/AptDealList"),
      },
    ],
  },
  {
    path: "/user",
    component: UserView,
    children: [
      {
        path: "regist",
        name: "regist",
        component: () => import("@/components/user/UserRegist"),
      },
      {
        path: "/:id",
        name: "detail",
        component: () => import("@/components/user/UserDetail"),
      },
      {
        path: "login",
        name: "login",
        component: () => import("@/components/user/UserLogin"),
      },
      {
        path: "update",
        name: "update",
        component: () => import("@/components/user/UserUpdate"),
      },
      {
        path: "findId",
        name: "findId",
        component: ()=>import("@/components/user/UserFindId"),
      },
      {
        path: "findPw",
        name: "findPw",
        component: ()=>import("@/components/user/UserFindPw"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
