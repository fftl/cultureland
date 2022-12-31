<template>
  <b-container>
    <b-row>
      <b-col>
        <h1 id="title_header">FindPw</h1>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col >
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert v-if="find_error" variant="danger" > 비밀번호를 찾을 수 없습니다. </b-alert>
            <b-form-group label-for="username">
              <b-form-input
                id="username"
                v-model="user.username"
                required
                placeholder="ID"
              ></b-form-input>
            </b-form-group>
            <b-form-group label-for="name">
              <b-form-input
                type="text"
                id="name"
                v-model="user.name"
                required
                placeholder="NAME"
              ></b-form-input> 
            </b-form-group>
            <b-form-group label-for="email">
              <b-form-input
                type="email"
                id="email"
                v-model="user.email"
                required
                placeholder="EMAIL"
              ></b-form-input> 
            </b-form-group>
            <p><button type="button" id="user_button" @click="findPw">비밀번호 찾기</button></p>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>
<script>
import { mapActions } from 'vuex'
const userStore = "userStore";
export default {
  name: "UserFindPw",
  data(){
    return{
      user:{
        username:"",
        name:"",
        email:"",
      },
      find_error:false, 
    }
  },
  methods:{
    ...mapActions(userStore, ["findPassword"]),
    async findPw(){
      const result = await this.findPassword(this.user);
      console.log(result);
      if(result){
        alert("임시비밀번호를 EMAIL로 전송하였습니다. 로그인 후 비밀번호를 변경해주세요.");
      } else {
        alert("입력하신 정보를 찾을 수 없습니다.")
      }
    }
  }
}
</script>
<style scoped>
@import "@/assets/css/user.css";
</style>