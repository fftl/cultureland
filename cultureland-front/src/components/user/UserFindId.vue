<template>
  <b-container>
    <b-row>
      <b-col>
        <h1 id="title_header">FindId</h1>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col >
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="SET_IS_FINDID"> 아이디를 찾을 수 없습니다. </b-alert>
            <b-form-group label-for="name">
              <b-form-input
                id="name"
                v-model="user.name"
                required
                placeholder="본명"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label-for="email">
              <b-form-input
                type="email"
                id="email"
                v-model="user.email"
                required
                placeholder="이메일"
                @keyup.enter="confirm"
              ></b-form-input> 
            </b-form-group>
            <p><button type="button" id="user_button" @click="findUsername">아이디 찾기</button></p>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>
<script>
import {  mapState, mapActions } from 'vuex'
const userStore = "userStore";
export default {
  name: "UserFindId",
  data(){
    return {
      user :{
        name : null,
        email : null
      }
    }
  },
  computed:{
    ...mapState(userStore,["SET_IS_FINDID"]),
    // 가능하다면, SET_IS_FINDID를 사용해서,
    // 찾을 수 없다면, danger로 보여주고 찾았다면 파란색으로다가 id 보옂주고싶어, 근데 state가 제대로 안불러와짐,,
  },
  methods:{
    ...mapActions(userStore,["findId"]),

    async findUsername(){
      let result = await this.findId(this.user);
      if(result){
        alert("찾으신 id는 "+result+"입니다.");
      }
      else{
        alert("찾으시는 id가 없습니다. 다시 입력해주세요.");
      }
    }
  },
}
</script>
<style scoped>

</style>
