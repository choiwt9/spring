package com.kh.opendata.run;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.vo.AirVO;

import java.io.BufferedReader;
import java.io.IOException;

public class AirPollutionApp {

	public static void main(String[] args) throws IOException  {
		//요청 주소 (url)
		 StringBuilder urlBuilder = new StringBuilder(""); /*URL*/
	    //서비스 인증 키
		 String myKey = "";//인코딩 키
		// String myKey = "";//디코딩키
	
		 //요청 파라미터   
		    urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + myKey); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("Json", "UTF-8")); /*xml 또는 json*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("sidoName","UTF-8") + "=" + URLEncoder.encode("서울", "UTF-8")); /*시도 이름(전국, 서울, 부산, 대구, 인천, 광주, 대전, 울산, 경기, 강원, 충북, 충남, 전북, 전남, 경북, 경남, 제주, 세종)*/
	        urlBuilder.append("&" + URLEncoder.encode("ver","UTF-8") + "=" + URLEncoder.encode("1.0", "UTF-8")); /*버전별 상세 결과 참고*/
	       
	        //요청 주소와 함꼐 URL 객체를 생성
	        URL url = new URL(urlBuilder.toString());
	        
	        //url객체를 사용해 HttpURLConnection 객체 생성
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        
	        //요청 시 필요한 헤더 정보 세팅
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	        	//요청 성공 시 
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	        	//요청 실패 시
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        
	        
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        
	        //응답 데이터 출력
	        System.out.println(sb.toString());
	        
	        //응답 데이터를 Json형태로 파싱 작업 필요
	        //JsonObject, JsonArray, JsonElement 이용해서 파싱(Gson 라이브러리)
	        
	        JsonObject jobj = JsonParser.parseString(sb.toString()).getAsJsonObject();
	        JsonObject responseObj = jobj.getAsJsonObject("response");
	        JsonObject bodyObj = responseObj.getAsJsonObject("body");
	       
	        		
	        System.out.println(bodyObj);		
	        //totalCount, items 값을 추출
	         int totalCount = bodyObj.get("totalCount").getAsInt();
	         JsonArray items = bodyObj.getAsJsonArray("items");
	         
	      //   System.out.println("totalCount : " + totalCount);
	       //  System.out.println(items);
	         
	         ArrayList<AirVO> list = new ArrayList<>();
	         for(int i = 0; i<items.size(); i++) {
	        	 JsonObject item = items.get(i).getAsJsonObject();
	        	// System.out.println(item);
	        	 
	        	 AirVO air = new AirVO();
	        	 air.setStationName(item.get("stationName").getAsString());
	        	 air.setDataTime(item.get("dataTime").getAsString());
	        	 air.setKhaiValue(item.get("khaiValue").getAsString());
	        	 air.setCoValue(item.get("coValue").getAsString());
	        	 air.setNo2Value(item.get("no2Value").getAsString());
	        	 air.setO3Value(item.get("o3Value").getAsString());
	        	 air.setPm10Value(item.get("pm10Value").getAsString());
	        	 air.setSo2Value(item.get("so2Value").getAsString());
	        
	           list.add(air);
	         }
	         for(AirVO a : list) {
	        	 System.out.println(a);
	         }
	}

}
