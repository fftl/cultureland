package com.ssafy.culture.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.culture.dto.Culture;
import com.ssafy.culture.service.CultureService;

@RequestMapping("/culture")
@RestController
public class CultureController {

	private CultureService cultureService;

	@Autowired
	public CultureController(CultureService cultureService) {
		super();
		this.cultureService = cultureService;
	}

	//문화공간 정보 가져오기 문화공간 데이터를 API를 이용해서 가져옵니다.
	//강의실의 네트워크로 요청을 할 수가 없어써 CSV파일을 가져와 직접 DB에 넣어주었기에 실제로
	//사용하지는 않았습니다.
	@GetMapping("/api")
	public String getApiCulture() {
		StringBuffer result = new StringBuffer();
		String jsonPrintString = null;
		JSONObject jsonObject = null;
		try {
			String key = "4475415a4170736837346d6f757258";
			String apiURL = "http://openapi.seoul.go.kr:8088/"+key+"/xml/culturalSpaceInfo/0/999";
			URL url = new URL(apiURL);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setConnectTimeout(5000);
			urlConnection.connect();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream, "UTF-8"));
			String returnLine;
			while ((returnLine = bufferedReader.readLine()) != null) {
				result.append(returnLine);
			}

			jsonObject = XML.toJSONObject(result.toString());
			jsonPrintString = jsonObject.toString();
			
			JSONObject form = (JSONObject) jsonObject.get("culturalSpaceInfo");
			JSONArray array = (JSONArray) form.get("row");
			
			for(int i=0; i<array.length(); i++) {
				cultureService.addCulture(getMapFromJSONObject((JSONObject) array.get(i)));
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonPrintString;
	}
	
	//모든 문화 공간 정보를 반환합니다.
	@GetMapping
	public ResponseEntity<List<Culture>> getAllCulture() {
		List<Culture> allCulture = cultureService.getAllCulture();
		return new ResponseEntity<List<Culture>> (allCulture, HttpStatus.ACCEPTED);
	}
	
	/*
	 * ===============================================================================================================================================================================================================
	 * ===============================================================================================================================================================================================================
	 * 기능들
	 * */
	
	public static Map<String, Object> getMapFromJSONObject(JSONObject obj) {
        if (ObjectUtils.isEmpty(obj)) {
            throw new IllegalArgumentException(String.format("BAD REQUEST obj %s", obj));
        }

        try {
            return new ObjectMapper().readValue(obj.toString(), Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json array 를 list map 으로 변경.
     *
     * @param jsonArray
     * @return 값이 있으면 list map, 없으면 list 빈 값 return
     */
    public static List<Map<String, Object>> getListMapFromJsonArray(JSONArray jsonArray) {
        if (ObjectUtils.isEmpty(jsonArray)) {
            throw new IllegalArgumentException("jsonArray is null");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (Object jsonObject : jsonArray) {
            list.add(getMapFromJSONObject((JSONObject) jsonObject));
        }
        return list;
    }
}
