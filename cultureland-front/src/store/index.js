import Vue from 'vue'
import Vuex from 'vuex'
// import createPersistedState from "vuex-persistedstate";

//module import
import userStore from "@/store/modules/userStore"
import cultureStore from "@/store/modules/cultureStore"
import aptStore from "@/store/modules/aptStore"

Vue.use(Vuex)

export default new Vuex.Store({
  modules:{
    userStore,
    cultureStore,
    aptStore
  },
  plugins:[
    // createPersistedState({
    //   storege: sessionStorage,
    // }),
  ]
});
