<template>
  <div id="userRegist">
    <b-container>
      <b-row>
        <b-col>
          <h1 id="title_header">SignUp</h1>
        </b-col>
      </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="5">
          <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
            <b-form class="text-left">
              <b-form-group label-for="userid">
                <b-form-input
                  id="userid"
                  v-model="user.username"
                  required
                  placeholder="아이디"
                  @keyup.enter="confirm"
                  @blur="idCheck"
                ></b-form-input>
              </b-form-group>
              <b-form-group label-for="name">
                <b-form-input
                  id="name"
                  v-model="user.name"
                  required
                  placeholder="본명"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-form-group label-for="userpwd">
                <b-form-input
                  type="password"
                  id="userpwd"
                  v-model="user.password"
                  required
                  placeholder="비밀번호"
                  @keyup.enter="confirm"
                  @blur="pwCheck"
                ></b-form-input>
              </b-form-group>
              <b-form-group label-for="userpwd">
                <b-form-input
                  type="password"
                  id="userpwd"
                  v-model="user.passwordCheck"
                  required
                  placeholder="비밀번호 확인"
                  @keyup.enter="confirm"
                  @keyup="pwCheck"
                ></b-form-input>
              </b-form-group>
              <b-alert show variant="danger"  v-if="!pwIsEqual"> 비밀번호가 같지 않습니다. </b-alert> 
              <b-form-group label-for="nickname">
                <b-form-input
                  id="nickname"
                  v-model="user.nickname"
                  required
                  placeholder="닉네임"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>

              <b-form-group label-for="email">
                <b-form-input
                  id="email"
                  v-model="user.email"
                  required
                  placeholder="이메일"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>

              <button type="button" id="user_button" @click="confirm">회원 등록</button>
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import {mapActions} from 'vuex';
const userStore = "userStore";
export default {
  name: "UserRegist",
  data() {
    return {
      // isLoginError: false,
      user: {
        username: null,
        name : null,
        password: "",
        passwordCheck: "",
        nickname: null,
        email: null
      }
      ,pwIsEqual : true,
    };
  },
  methods:{
    ...mapActions(userStore, ["userRegist", "idcheck"]),

    confirm(){

        if(!this.user.email || !this.user.name || !this.user.nickname || !this.user.username)
        {
          alert("양식을 다 채워주세요");
        }
        else if(!this.pwIsEqual){
          alert("비밀번호를 확인해주세요");
        }
        else{
          this.userRegist(this.user);
          this.$router.push({ name: "home" });
        }
    },
    idCheck(){
      console.log(this.idcheck(this.user))
    },
    pwCheck(){
      if(this.user.password && this.user.passwordCheck){
        if(this.user.password === this.user.passwordCheck){
          this.pwIsEqual=true;
        } else {
          this.pwIsEqual=false;
        }
      } else {
        this.pwIsEqual=false;
      }
    }
  },
}
</script>
<style>
@import "@/assets/css/user.css";
</style>