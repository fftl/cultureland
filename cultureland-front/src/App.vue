<template>
  <div id="app">
    <header-nav/>
    <router-view/>
    <footer-nav/>
  </div>
</template>
<script>
import HeaderNav from "@/components/common/HeaderNav.vue"
import FooterNav from "@/components/common/FooterNav.vue"

export default {
  name:"App",
  components:{
    HeaderNav,
    FooterNav,
  }
}
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
.button{
    background-color: white;
    border: 1px solid #398a46;
    color:#398a46;
    width: 90px;
    border-radius: 1rem;
    padding-top: 8px;
    padding-bottom: 3px;
    text-align: center;
    font-size: 15px;
    margin-left:10px;
}
.button > a {
  color:#398a46;
}
</style>
