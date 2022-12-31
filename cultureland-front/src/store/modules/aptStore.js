import {getAptDeals, getAptInfos, getGuguns, getDongs} from "@/api/apt.js";

const aptStore = {
  namespaced: true,
  state: {
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    aptInfos: [],
    aptDeals: [],
    center: { lat: 0, lng: 0 },
    listKey: false,
  },
  getters: {},
  mutations: {
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },

    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },

    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.code, text: gugun.name });
      });
    },

    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        //서울특별시 도봉구
        //서울특별시 도봉구 쌍문동 같은 경우를 제거하기 위해서!!
        var nowName = dong.name.split(" ")[2];
        if (nowName != null)
          state.dongs.push({ value: dong.code, text: nowName });
      });
    },

    SET_APT_INFOS(state, aptInfos) {
      aptInfos.forEach((aptInfo) => {
        state.aptInfos.push({
          aptCode: aptInfo.aptCode,
          aptName: aptInfo.aptName,
          lat: aptInfo.lat,
          lng: aptInfo.lng,
          buildYear: aptInfo.buildYear,
          dealCnt: aptInfo.dealCnt,
          avgDealAmount: aptInfo.avgDealAmount,
        });
      });
    },

    SET_APT_DEALS(state, aptDeals) {
      aptDeals.forEach((aptDeal) => {
        state.aptDeals.push({
          dealNo: aptDeal.dealNo,
          aptCode: aptDeal.aptCode,
          dealAmount: aptDeal.dealAmount,
          dealYear: aptDeal.dealYear,
          dealMonth: aptDeal.dealMonth,
          dealDay: aptDeal.dealDay,
          area: aptDeal.area,
          floor: aptDeal.floor,
        });
      });
    },

    CLEAR_APT_INFOS(state) {
      state.aptInfos = [];
    },

    CLEAR_APT_DEALS(state) {
      state.aptDeals = [];
    },

    SET_LIST_KEY(state,value) {
      state.listKey = value;
    },

    SET_CENTER(state, position) {
      state.center.lat = position.lat;
      state.center.lng = position.lng;
    },
  },
  actions: {
    getGugun: ({ commit }) => {
      getGuguns(
        ({ data }) => {
          console.log("구군성공!");
          commit("SET_GUGUN_LIST", data.regcodes);
        },
        (error) => {
          console.log("구군에러!");
          console.log(error);
        }
      );
    },

    getDong: ({ commit }, gugunCode) => {
      getDongs(
        gugunCode,
        ({ data }) => {
          console.log("동성공!");
          commit("SET_DONG_LIST", data.regcodes);
        },
        (error) => {
          console.log("동에러!");
          console.log(error);
        }
      );
    },

    getAptInfo: ({ commit }, dongCode) => {
      getAptInfos(
        dongCode,
        ({ data }) => {
          if (data.length > 0) {
            commit("CLEAR_APT_INFOS");
            commit("SET_APT_INFOS", data);
          } else {
            alert("거래 데이터가 존재하지 않습니다.");
            commit("CLEAR_APT_INFOS");
          }
        },
        (error) => {
          console.log("아파트 정보 가져오기 에러!");
          console.log(error);
        }
      );
    },

    getAptDeal: ({ commit }, aptCode) => {
      console.log("action getAptDeal >>" + aptCode);
      getAptDeals(
        aptCode,
        ({ data }) => {
          console.log(data);
          if (data.length > 0) {
            commit("CLEAR_APT_DEALS");
            commit("SET_APT_DEALS", data);
          } else {
            alert("거래 데이터가 존재하지 않습니다.");
          }
        },
        (error) => {
          console.log("아파트 정보 가져오기 에러!");
          console.log(error);
        }
      );
    },
  },
};

export default aptStore;
