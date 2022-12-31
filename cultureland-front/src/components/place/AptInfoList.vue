<template>
  <b-container id="aptlist" v-if="aptInfos" class="bv-example-row mt-3">
    <apt-info-list-item v-for="(aptInfo, index) in aptInfos" :key="index" :getAptInfo="aptInfo" />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show>아파트 목록이 없습니다.</b-alert>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import AptInfoListItem from "@/components/place/AptInfoListItem";
import { mapState } from "vuex";
const aptStore = "aptStore";

export default {
  name: "AptInfoList",
  components : {
    AptInfoListItem
  },
  data() {
    return {
    };
  },
  computed: {
    ...mapState(aptStore, ["aptInfos"])
  },
  created() {
  },
  methods: {
  }
};
</script>

<style scoped>

#aptlist {
  height : 500px;
  width : 400px;
}

</style>