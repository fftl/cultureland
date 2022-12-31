<template>
        <b-row class="m-3" >
          <b-card  
          header-tag="header" 
          footer-tag="footer" 
          style="min-width:20rem; max-width: 20rem;"
          >
            <template #header>
              <h3 class="mb-2">{{ getAptInfo.aptName }}</h3>
            </template>
            <b-card-text>
            <span>  총 <strong>{{ getAptInfo.dealCnt}}</strong>회 거래  </span><br>
            <span>  평균 <strong>{{ getAptInfo.avgDealAmount}}</strong> 만원 </span>
            <p>{{ getAptInfo.buildYear}}년 건축</p>
            </b-card-text>
            <b-button id="user_button" @click="setCenter">detail</b-button>

          </b-card>
        </b-row>
</template>

<script>
import { mapMutations, mapState, mapActions } from 'vuex';
const aptStore = "aptStore";
const cultureStore = "cultureStore";
export default {
  name: "AptInfoListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    getAptInfo : Object,
  },
  created () {
  },
  methods: {
    ...mapMutations(aptStore, ["SET_CENTER", "SET_LIST_KEY"]),
    ...mapMutations(cultureStore, ["SET_NEAR_CULTURES"]),
    ...mapActions(aptStore, ["getAptDeal"]),

    async setCenter(){
        const position = {lat: this.getAptInfo.lat , lng: this.getAptInfo.lng };
        await this.SET_CENTER(position);
        await this.SET_LIST_KEY(true);
        this.getAptDeal(this.getAptInfo.aptCode)
        let sortArr = this.findNearCulture();

        
        let nearCultures = [];
        nearCultures.push(sortArr[0]);
        nearCultures.push(sortArr[1]);
        nearCultures.push(sortArr[2]);

        this.SET_NEAR_CULTURES(nearCultures);

    },

    //가장 가까운 문화공간을 탐색합니다.
    findNearCulture(){
      console.log(this.cultures);
      let sortArr = this.cultures.sort((a, b) => {
        let numA = this.getDistance(a.lat, a.lng, this.getAptInfo.lat , this.getAptInfo.lng);
        let numB = this.getDistance(b.lat, b.lng, this.getAptInfo.lat , this.getAptInfo.lng);

        if (numA > numB) return 1;
        else return -1;
      });

      return sortArr;
    },

    getDistance(lat1, lon1, lat2, lon2) {
    if ((lat1 == lat2) && (lon1 == lon2))
        return 0;

    var radLat1 = Math.PI * lat1 / 180;
    var radLat2 = Math.PI * lat2 / 180;
    var theta = lon1 - lon2;
    var radTheta = Math.PI * theta / 180;
    var dist = Math.sin(radLat1) * Math.sin(radLat2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radTheta);
    if (dist > 1)
        dist = 1;

    dist = Math.acos(dist);
    dist = dist * 180 / Math.PI;
    dist = dist * 60 * 1.1515 * 1.609344 * 1000;
    if (dist < 100) dist = Math.round(dist / 10) * 10;
    else dist = Math.round(dist / 100) * 100;

    return dist;
}

  },
  computed:{
    ...mapState(aptStore, ["center"]),
    ...mapState(cultureStore, ["cultures"])
  }
};
</script>

<style scoped>
@import "@/assets/css/user.css";
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
#aptInfoContent {
  font-weight: bold;
  background-color: lightyellow;
}
</style>
