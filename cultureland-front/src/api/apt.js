import { addressInstance, apiInstance } from "./index.js";

// const api = apiInstance();
const api = apiInstance();
const address = addressInstance();

async function getGuguns(success, fail) {
  const addUrl = "regcode_pattern=11"+"*00000&is_ignore_zero=true";
  await address.get(`?`+addUrl).then(success).catch(fail);
}

async function getDongs(gugunCode, success, fail) {
  const addUrl = "regcode_pattern="+gugunCode+"*&is_ignore_zero=true";
  await address.get(`?`+addUrl).then(success).catch(fail);
}

async function getAptInfos(dongCode, success, fail) {
  const addUrl = `/apt/dong/${dongCode}`;
  await api.get(addUrl).then(success).catch(fail);
}

async function getAptDeals(aptCode, success, fail) {
  const addUrl = `/apt/apt/${aptCode}`;
  await api.get(addUrl).then(success).catch(fail);
}

export { getGuguns, getAptInfos, getDongs, getAptDeals };


