<template>
  <div class="main">
    <div class="map">
      <side-bar id="sidebar"/>
      <kakao-map id="kakaomap"/>
    </div>
  </div>
</template>
<script>

import KakaoMap from '@/components/place/KakaoMap.vue';
import SideBar from '@/components/place/SideBar.vue';

export default {
  name: 'MainView',
  components: {
    KakaoMap, 
    SideBar
  }
}
</script>
<style scoped>
.map {
  text-align: center;
  height: 600px;
}
#sidebar{
  position: absolute;
  margin-left: 1%;
  z-index: 100;
  width :400px;
}
</style>