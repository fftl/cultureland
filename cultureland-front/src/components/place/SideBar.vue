<template>
<div id="sidebar">
  <b-row class="mt-4 mb-4 text-center">
    <b-col class="sm-3">
      <b-form-select v-model="gugunCode" :options="guguns" @change="getDongThis"></b-form-select>
    </b-col>
    <b-col class="sm-3">
      <b-form-select v-model="dongCode" :options="dongs" @change="selectDongThis"></b-form-select>
    </b-col>
  </b-row>
  <!--Detail일 경우-->
  <div v-if="listKey">
    <apt-deal-list/>
  </div>
  <!--Detail이 아닐 경우-->
  <div v-else  id="aptinfolist">
    <apt-info-list/>
  </div>
</div>
</template>

<script>
import AptInfoList from "@/components/place/AptInfoList";
import AptDealList from "@/components/place/AptDealList";
import { mapActions, mapState, mapMutations } from 'vuex';
const aptStore = "aptStore";

export default {
  name: "SideBar",
  components : {
    AptInfoList,
    AptDealList,
  },
  data() {
    return {
      dongCode: null,
      gugunCode: null,
    };
  },
  computed: {
    ...mapState(aptStore, ["guguns", "dongs", "aptInfos","listKey"]),
  },

  
  created() {
    this.CLEAR_GUGUN_LIST();
    this.getGugun();
  },

  methods: {
    ...mapActions(aptStore, ["getGugun", "getDong", "getAptInfo"]),
    ...mapMutations(aptStore, ["CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST", "CLEAR_APT_INFOS", "CLEAR_APT_DEALS", "SET_LIST_KEY"]),

    async getDongThis(){
      await this.CLEAR_DONG_LIST();
      await this.CLEAR_APT_INFOS();
      await this.CLEAR_APT_DEALS();
      await this.SET_LIST_KEY(false);
      let code = this.gugunCode;
      let goCode = "";
      for(const element of code){
        if(element != "0"){
          goCode += element;
        } else {
          break;
        }
      }
      await this.getDong(goCode);
      this.dongCode = null;
    },

    selectDongThis(){
      this.getAptInfo(this.dongCode);
    }
  },
};
</script>
<style scoped>
#sidebar{
  background-color:rgba(217, 217, 217, 0.7);
}
#aptinfolist{
  overflow-y:scroll;
  justify-content: center;
}
</style>