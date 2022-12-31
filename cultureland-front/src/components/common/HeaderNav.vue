<template>
    <header>
        <nav class="header-nav">
            <div class="header-left">
                <b-navbar-brand href="#">
                    <router-link to="/">
                        <img src="@/assets/logo.png" alt="logo"  class="logo">
                    </router-link>
                </b-navbar-brand>
            </div>
            <div>
            </div>
            <div class="header-right">
                    <div v-if="isLogin" class="mybox">                                  
                                <div id="mytext" style="font-size: 20px">
                                    <strong>{{userInfo.nickname}}</strong>님, 안녕하세요!
                                </div>
                                <button class="button">
                                    <router-link :to= "{ name: 'detail' }">내 정보</router-link>
                                </button>
                                <button class="buttonlogout" @click="onclickLogOut">
                                    로그아웃
                                </button>                    
                    </div>
                    <div v-else class="mybox">
                    <div>
                        <button class="button"><router-link :to="{ name: 'login' }">로그인</router-link></button>
                        <button class="button"><router-link :to="{ name: 'regist' }">회원가입</router-link></button>
                    </div>
                </div>
            </div>
        </nav>
    </header>
</template>
<script>
import { mapState, mapActions } from 'vuex';

const userStore = "userStore";

export default {
    name : "HeaderNav",

    data() {
        return {};
    },
    computed: {
        ...mapState(userStore, ["isLogin","userInfo"]),
        },
    methods: {
        ...mapActions(userStore, ["userLogout"]),
        onclickLogOut(){
            this.userLogout(this.userInfo.user_id);
            sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
            sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
            if (this.$route.path != "/") this.$router.push({ name: "home" });
        }
    },
}
</script>
<style>

header {
    background-color:#64A36F;
}
/* 양쪽으로 배치 */
.header-nav{
    display: flex;
    justify-content: space-between;
}
.logo{
    width: 70%;
}
.mybox{
    width: 550px;
    padding: 0px 5% 10px;
    text-align:center;   
    margin-top:2%;
}
.mybox > div {
    display: inline-block;
    margin: 5px 2px;
}
.mybox > div:nth-child(2){
    margin: 0px 5px;
}
.button{
    background-color: white;
    border: 1px solid #398a46;
    color:#398a46;
    width: 90px;
    border-radius: 5rem;
    padding-top: 8px;
    padding-bottom: 3px;
    text-align: center;
    font-size: 15px;
}
.buttonlogout{
    background-color:  #398a46;
    border: 1px solid #398a46;
    color:#FFE121;
    width: 90px;
    border-radius: 5rem;
    padding-top: 8px;
    padding-bottom: 3px;
    text-align: center;
    display: inline-block;
    font-size: 15px;
}
.mybox > button {
    margin-left:10px;
}
</style>