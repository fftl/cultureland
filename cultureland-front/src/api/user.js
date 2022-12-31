import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function regist(user, success, fail) {
  await api.post(`/user`, JSON.stringify(user)).then(success).catch(fail);
}

async function withdraw(userid, success, fail){
	await api.delete(`/user/${userid}`).then(success).catch(fail);
}

async function update(user, success, fail){
	await api.put('/user',JSON.stringify(user)).then(success).catch(fail);
}

async function findPassword(user, success, fail){
	await api.post('/user/findpwd', JSON.stringify(user)).then(success).catch(fail);
}

async function findId(user,success,fail){
  await api.post('/user/findid', JSON.stringify(user)).then(success).catch(fail);
}

async function idcheck(user, success, fail) {
  await api.post("/user/idcheck", JSON.stringify(user)).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

export { login, findById, findPassword, findId, logout, tokenRegeneration, regist, withdraw, update, idcheck};
