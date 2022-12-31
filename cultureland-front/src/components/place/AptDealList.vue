<template>
  <div>
    <b-container v-if="aptDeals" class="bv-example-row mt-3">
      <button id="back_btn" @click="back">목록으로 돌아가기</button>
      <h3>거래내역</h3>
      <hr>
      <div id="deallist">
        <apt-deal-list-item
          v-for="(aptDeal, index) in aptDeals"
          :key="index"
          :getDealInfo="aptDeal"
        />
      </div>
      <br>
      <h3>문화공간</h3>
      <hr>
      
      <div id="culturelist">
        <culture-info />
      </div>
    </b-container>
  </div>
</template>

<script>
import AptDealListItem from "@/components/place/AptDealListItem";
import CultureInfo from "@/components/place/CultureInfo";
import { mapState, mapMutations } from "vuex";
const aptStore = "aptStore";

export default {
  name: "AptDealList",
  components: {
    AptDealListItem,
    CultureInfo
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(aptStore, ["aptDeals"]),
    checkAptDeals() {
      return this.aptDeals;
    }
  },
  created() {
    console.log("APTDEALS");
    console.log(this.aptDeals);
  },
  methods: {
    ...mapMutations(aptStore, ["SET_LIST_KEY"]),
    back() {
      this.SET_LIST_KEY(false);
    }
  },
  watch: {
    checkAptDeals() {}
  }
};
</script>

<style scoped>
#sidebar {
  width: 500px;
}

#deallist {
  height: 150px;
  overflow-y: scroll;
  justify-content: center;
}

#culturelist {
  height: 250px;
  overflow-y: scroll;
  justify-content: center;
}
#back_btn {
  background-color:rgb(84, 138, 57);
  border: none;
  color: white;
  width: 174px;
  height: 40px;
  margin-bottom:15px;
}
#back_btn:hover{
  background-color:rgb(60, 100, 57);
}
</style>