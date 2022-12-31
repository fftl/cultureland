import {getCultures} from "@/api/culture.js";

const cultureStore = {
  namespaced: true,
  state: {
    cultures:[],
    nearCultures:[]
  },
  mutations: {
    SET_CULTURES:(state, culturesData) => {
      state.cultures = culturesData;
    },
    SET_NEAR_CULTURES:(state, nearCultures) => {
      state.nearCultures = nearCultures;
    }
  },
  actions: {
    //db에 저장된 culture 정보를 가져와 cultures에 넣어준다.
    async getCultures({commit}){
      await getCultures(
        ({data}) => {
          commit("SET_CULTURES", data);
        },
        (error) => {
          console.log(error);
        }
      )
    }
  },
};

export default cultureStore;
