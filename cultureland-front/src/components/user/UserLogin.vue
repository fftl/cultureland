<template>
  <b-container>
    <b-row>
      <b-col>
        <h1 id="title_header">Login</h1>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col >
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError">아이디 또는 비밀번호를 확인하세요.</b-alert>
            <b-form-group label-for="username">
              <b-form-input
                id="username"
                v-model="user.username"
                required
                placeholder="ID"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label-for="password">
              <b-form-input
                type="password"
                id="password"
                v-model="user.password"
                required
                placeholder="PW"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <p><button type="button" id="user_button" @click="confirm">로그인</button></p>
            <p><button type="button" id="user_button" @click="movePage">회원가입</button></p>
            <div id="find_anchor">
              <span><router-link :to="{ name: 'findId' }">아이디 찾기</router-link></span>
              <span><router-link :to="{ name: 'findPw' }">비밀번호 찾기</router-link></span>
            </div>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>
<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "UserLogin",
  data() {
    return {
      // isLoginError: false,
      user: {
        username: null,
        password: null
      }
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"])
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    
    async confirm() {
      console.log(this.user);
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      console.log("login  후 토큰 !! >> "+ token);
      if (this.isLogin) { 
        console.log("isLogin if에 들어왔습니다.")
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      } else {
        alert("올바른 아이디, 비밀번호를 입력해주세요.")
      }
    },
    movePage() {
      this.$router.push({ name: "regist" });
    }
  }
};
</script>
<style>
@import '@/assets/css/user.css';

</style>