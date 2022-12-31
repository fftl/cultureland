<template>
<div>
    <div v-for="(culture, index) in nearCultures" :key="index">
        <div>
        <b-card no-body class="overflow-hidden" style="max-width: 540px;">
            <b-row >
            <b-col md="5">
                <b-card-img :src="culture.img_url" alt="Image" class="rounded-0"></b-card-img>
            </b-col>
            <b-col md="7">
                <b-card-text>
                    <h4>{{culture.content}}</h4>                  
                    <span>{{culture.c_category}} </span><br>                 
                    {{culture.address}}
                    <p>☎️ {{culture.tel}}</p>
                </b-card-text>
            </b-col>
            </b-row>
        </b-card>
        </div>
    </div>
</div>
</template>

<script>
import { mapState } from 'vuex';
const cultureStore = "cultureStore";
export default {
  name: "CultureInfo",
  components: {
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(cultureStore, ["nearCultures"]),
    checkNearCultures(){
        return this.nearCultures;
    }
  },
  methods: {
  },
  watch: {
    checkNearCultures(){
        console.log("근처의 문화공간이 변경되었어요!!");
        console.log(this.nearCultures[0]);
    }
  }
};
</script>

<style scoped>
#sidebar {
  width: 500px;
}

#deallist {
  height: 300px;
  overflow-y:scroll;
  justify-content: center;
}
</style>