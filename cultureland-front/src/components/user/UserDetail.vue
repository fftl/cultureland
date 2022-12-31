<template>
  <!-- <b-container class="mt-4" v-if="userInfo"> -->
    <b-container>
      <b-row>
        <b-col>
          <h1 id="title_header">MyPage</h1>
        </b-col>
      </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="5">
          <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
            <b-form class="text-left">
              <strong>ID</strong>
              <b-form-group label-for="userid">
                <b-form-input
                  id="userid"
                  v-model="userInfo.username"
                  required
                  placeholder="아이디"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <strong>닉네임</strong>
              <b-form-group label-for="nickname">
                <b-form-input
                  id="nickname"
                  v-model="userInfo.nickname"
                  required
                  placeholder="닉네임"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <strong>email</strong>
              <b-form-group label-for="email">
                <b-form-input
                  id="email"
                  v-model="userInfo.email"
                  required
                  placeholder="이메일"
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>

              <button type="button" id="user_button" @click="modifyUser">정보 수정</button>
              <button type="button" id="user_button" @click="deleteUser" style="background-color:red;">회원탈퇴</button>
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
</template>
<script>
import { mapActions, mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "UserDetail",
  components: {},
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods:{
    ...mapActions(userStore,["userDelete", "userModify"]),
 
    // 회원 정보 수정
    async modifyUser(){
      await this.userModify(this.userInfo);
    },
    // 회원 탈퇴 method
    async deleteUser(){
      await this.userDelete(this.userInfo.user_id);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      this.movePage();
    },

    movePage() {
      this.$router.push({ name: "home" });
    }
  },
};
</script>
<style>

</style>