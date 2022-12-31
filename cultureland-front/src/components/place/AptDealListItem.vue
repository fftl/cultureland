<template>
  <b-row
    class="m-2"
    @click="setCenter"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col id="aptInfoContent" class="align-self-center"> 
      ({{ getDealInfo.floor}}층)
      {{ getDealInfo.area }}평, 
      {{ getDealInfo.dealAmount }}원,
      <span style="color:lightgray">{{ getDealInfo.dealYear}}년     
      {{ getDealInfo.dealMonth}}월
      {{ getDealInfo.dealDay}}일  
      </span>   
    </b-col>
  </b-row>
</template>

<script>
import { mapMutations, mapState } from 'vuex';
const aptStore = "aptStore";
export default {
  name: "AptInfoListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    getDealInfo : Object,
  },
  created () {
    console.log(this.getDealInfo);
  },
  methods: {
    ...mapMutations(aptStore, ["SET_CENTER"]),

    async setCenter(){
        
        console.log("before >> ");
        // console.log(this.center);
        console.log(this.center.lat);

        console.log("setCenter >> ");
        console.log(this.getAptInfo);
        const position = {lat:this.getAptInfo.lat, lng:this.getAptInfo.lng};
        this.SET_CENTER(position);
        
        console.log("after >> ");
        // console.log(this.center);
        console.log(this.center.lat);
    }
  },
  computed:{
    ...mapState(aptStore, ["center"])
  }
};
</script>

<style scoped>
.apt {
  width: 50px;
}

#aptInfoContent {
  background-color: rgb(159, 159, 159,0.7);
}
#aptInfoContent:hover{
  background-color: rgb(159, 159, 159);
}
</style>
