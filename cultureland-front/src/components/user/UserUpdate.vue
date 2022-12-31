<template>
  <b-container>
    <b-row>
      <b-col></b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">

          <h1 id="title_header">MyPage</h1>
          <hr class="my-4" />
          <b-container class="mt-4" >
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.username }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.nickname }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />
          <b-button variant="primary" @click="goCheck" class="mr-1">
            <router-link :to="{ name: 'update' }">정보수정</router-link>
            </b-button>
          <b-button variant="danger" href="#">회원탈퇴</b-button>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>
<script>
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "UserDetail",
  components: {},
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>
<style>

</style>