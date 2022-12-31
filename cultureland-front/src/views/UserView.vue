<template>
    <div id="UserView">
        <router-view/>
    </div>
</template>
<script>

export default{
    name:"UserView",
}
</script>
<style scoped>
#UserView{
    background-image: url("../assets/web-background.jpg");
    background-size:cover;
    height: 720px;
}

</style>
