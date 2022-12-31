import jwtDecode from "jwt-decode";
import router from "@/router";
import { login, findById, findId, findPassword, logout, tokenRegeneration, regist, withdraw, update, idcheck } from "@/api/user";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isFindId:false,
  },
  getters: {
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    CLEAR_USER_INFO: (state) => {
      state.userInfo = null;
    },
    SET_IS_FINDID:(state, isFindId)=>{
      state.isFindId= isFindId;
    }
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        ({ data }) => {
          console.log(data);
          if (data.message === "success") {
            console.log("로그인 요청 성공!!");
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_IS_VALID_TOKEN", true);
            commit("SET_USER_INFO", data);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
            commit("SET_IS_VALID_TOKEN", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async getUserInfo({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      console.log("getUserInfo, decodeToken >> " + decodeToken.userId);
      await findById(
        decodeToken.userId,
        ({ data }) => {
          if (data.message === "success") {
            console.log(data);
            commit("SET_USER_INFO", data.userInfo);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.log(
            "getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ",
            error.response.status
          );
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
        }
      );
    },
    async tokenRegeneration({ commit, state }) {
      console.log(
        "토큰 재발급 >> 기존 토큰 정보 : {}",
        sessionStorage.getItem("access-token")
      );
      await tokenRegeneration(
        JSON.stringify(state.userInfo),
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            console.log("갱신 실패");
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              state.userInfo.userid,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
                commit("SET_IS_VALID_TOKEN", false);
                router.push({ name: "login" });
              },
              (error) => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              }
            );
          }
        }
      );
    },

    async userLogout({ commit }, userid) {
      await logout(
        userid,
        ({ data }) => {
          if (data.message === "success") {
            console.log("로그아웃 되었다!!");
            commit("SET_USER_INFO", null);
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_VALID_TOKEN", false);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async userRegist({ commit }, user) {
      console.log("action, user >>" + user.username);
      console.log("action, name >>" + user.name);
      console.log("action, password >>" + user.password);
      console.log("action, nickname >>" + user.nickname);
      console.log("action, email >>" + user.email);
      await regist(
        user,
        ({ data }) => {
          if (data.message === "success") {
            console.log(commit);
            alert("가입 성공!");
          } else {
            console.log("회원 가입 중 오류 발생!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async userDelete({ commit }, userid) {
      await withdraw(
        userid,
        ({ data }) => {
          console.log("회원탈퇴 !");
          console.log(data);
          commit("SET_USER_INFO", null);
          commit("SET_IS_LOGIN", false);
          commit("SET_IS_VALID_TOKEN", false);
        },
        (error) => {
          console.log("error");
          console.log(error);
        }
      );
    },

     async findId({commit},user){
      let result = null;
       await findId(
        user,
        ({data})=>{
            commit("SET_IS_FINDID",true);
            result = data;
          },
        (error)=>{
            commit("SET_IS_FINDID",false);
            console.log(error);
         },
      )

      return result;
    },
    
    async findPassword({commit} , user){
      var res = false;
      await findPassword(
        user,
        ({data}) => {
          commit;
          if(data.message == "success"){
              res = true;
            } else {
              res = false;
            }
          }
        )

        return res;
    },

    async userModify({ commit }, user) {
      await update(
        user,
        () => {
          console.log(commit);
        },
        (error) => {
          console.log("error");
          console.log(error);
        }
      );
    },

    async idcheck({ commit }, user) {
      await idcheck(
        user,
        ({ data }) => {
          if (data.message == "success") {
            commit("SET_IDCHECK", true);
          } else {
            commit("SET_IDCHECK", false);
          }
        },
        (error) => {
          console.log("error");
          console.log(error);
        }
      );
    },
  },
};

export default userStore;
