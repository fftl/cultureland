<template>
  <div>
    <div id="map"></div>
  </div>
</template>
<script>
import { mapActions, mapState } from "vuex";
const cultureStore = "cultureStore";
const aptStore = "aptStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      markers: [],
      infowindow: null
    };
  },
  //mounted ==========================================================================================
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=fd238029c35092754edfae10d0b0e6f9&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    }
  },

  //methods ==========================================================================================
  methods: {
    ...mapActions(cultureStore, ["getCultures"]),

    async initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.566824, 126.978649),
        level: 5
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      //문화 공간 정보를 모두 가져옵니다!
      await this.getCultures();

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      let zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      //this.displayMarker(letlngs);
      await this.setMarkerClusterer();
      // this.findMyPoint();
    },

    goCenter(lat, lng){
          var position = new kakao.maps.LatLng(lat, lng)
          this.map.setCenter(position);
    },

   //클러스터 띄우기!!
   setMarkerClusterer() {
      // 마커 클러스터러 생성
      let clusterer = new kakao.maps.MarkerClusterer({
        map: this.map,
        averageCenter: true,
        minLevel: 5
      });

      let imageSrc = require("@/assets/r_point.png"), // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(50, 50), // 마커이미지의 크기입니다
        imageOption = {}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      let markerImg = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );
      
      let markers = [];
      for (const element of this.cultures) {
        if (element.lat > 0 && element.lng > 0) {
          
          //마커 생성
          let marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(element.lat, element.lng),
            image: markerImg //마커이미지 생성
          });
          let message = `<div style="padding:5px; background-color:#FFE121;">${element.content}</div>`;
              message+= `<p> ${element.address}</p>`;
          // 인포윈도우를 생성합니다
          let infowindow = new kakao.maps.InfoWindow({
            content: message,
            removable: true
          });

          // 인포윈도우를 마커위에 표시합니다
          kakao.maps.event.addListener(marker, "click", () => {this.goCenter(element.lat, element.lng)});
          kakao.maps.event.addListener(marker, "mouseover", () => {infowindow.open(this.map, marker);});
          kakao.maps.event.addListener(marker,"mouseout", () => {infowindow.close(this.map, marker);});

          markers.push(marker);
        }
      }
      clusterer.addMarkers(markers);
    },

    displayMarker(aptInfos) {
      if (this.markers.length > 0) {
        this.markers.forEach(marker => marker.setMap(null));
      }

      //마커 커스텀 이미지 생성하기
      let imageSrc = require("@/assets/p_point.png"), // 마커이미지 [보라색]
        imageSize = new kakao.maps.Size(50, 50), // 마커이미지의 크기입니다
        imageOption = {}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      let markerImg = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
        );

      if (aptInfos.length > 0) {
        this.goCenter(aptInfos[0].lat, aptInfos[0].lng);
        for(var i=0; i<aptInfos.length; i++){
            let aptInfo = aptInfos[i];
            let marker = new kakao.maps.Marker({
              map: this.map,
              position : new kakao.maps.LatLng(aptInfo.lat, aptInfo.lng),
              image : markerImg,
            })

            this.markers.push(marker);

            // let message = `<div style="padding:5px; background-color:#FFE121;">${aptInfo.aptName}</div>`;
            // message+= `<p> 거래 횟수 : ${aptInfo.dealCnt}회 </p>`;
            // message+= `<p> 평균 금액 :${aptInfo.avgDealAmount}만원 </p>`;

            let message =`<div style="padding:5px; background-color:#57a839;"><strong>${aptInfo.aptName}</strong></div>
            <table border="1"><tr><th>거래 횟수</th><th>평균 금액</th></tr><tr><td>${aptInfo.dealCnt}</td><td>${aptInfo.avgDealAmount}</td></tr></table>`;
            // 인포윈도우를 생성합니다
            let infowindow = new kakao.maps.InfoWindow({
              content: message,
              removable: true
            });
              
            // 인포윈도우를 마커위에 표시합니다
            kakao.maps.event.addListener(marker, "click", () => { this.goCenter(aptInfo.lat, aptInfo.lng);});
            kakao.maps.event.addListener(marker, "mouseover", () => { infowindow.open(this.map, marker);});
            kakao.maps.event.addListener(marker, "mouseout", () => {infowindow.close(this.map, marker);});

            marker.setMap(this.map);
        }
      }
    },

    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable
      });

      this.map.setCenter(iwPosition);
    },

    // 지도에 마커와 인포윈도우를 표시하는 함수입니다
    myPointMarker(locPosition, message) {
      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        map: this.map,
        position: locPosition
      });

      var iwContent = message; // 인포윈도우에 표시할 내용
      var iwRemoveable = true;

      // 인포윈도우를 생성합니다
      var infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
        removable: iwRemoveable
      });

      // 인포윈도우를 마커위에 표시합니다
      infowindow.open(this.map, marker);

      // 지도 중심좌표를 접속위치로 변경합니다
      this.map.setCenter(locPosition);
    },

    

    findMyPoint() {
      if (navigator.geolocation) {
        console.log("사용할 수 있대!!");
        navigator.geolocation.getCurrentPosition((position) => {
          var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도

          var locPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
          var message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다

          // 마커와 인포윈도우를 표시합니다
          this.myPointMarker(locPosition, message);
          console.log("내 위치 완료!");
        });
      } else {
        console.log("navigator 사용할 수 없음");
      }
    }
  },

  //computed ==========================================================================================
  computed: {
    ...mapState(cultureStore, ["cultures"]),
    ...mapState(aptStore, ["aptInfos", "center"]),
    updateAptInfo() {
      return this.aptInfos;
    },

    //center 즉 배열로 하면은 변경을 감지하지못함?
    //배열의 내부 값을 직접 접근하면 변경을 볼 수 있다.
    updateCenter(){
      return this.center.lat;
    }
  },
  watch: {
    updateAptInfo(aptinfos) {
      console.log("updateAptInfo!! Test!! >>>> ");
      this.displayMarker(aptinfos);
    },

    updateCenter(){
      this.goCenter(this.center.lat, this.center.lng);
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 720px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
