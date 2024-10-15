package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.ResultSetMetaData;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {
// 서비스 키
	private final String SERVICE_KEY = ""/*인코딩 키*/;
	//대기오염 페이지 응답 메소드
	@RequestMapping("airPollution")
	public String showAirPollution() {
		return "airPollution";
	}
	//지진해일 긴급대피소 응답메소드
	@RequestMapping("shelter")
	public String showShelter() {
		return "tsunamiShelter";
	}
	
	
	
// air.do 요청을 받을 메소드
	@ResponseBody
	@RequestMapping(value="air.do", produces="application/json;charset=UTF-8")
	public String airPollution(String location, int pageNo) throws IOException {
		
		
		// 대기오염 정보를 조회(API사용)
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"
				     + "?serviceKey=" + SERVICE_KEY//URLEncoder.encode(SERVICE_KEY, "UTF-8") -> 디코딩인 경우
		             + "&sidoName=" + URLEncoder.encode(location, "UTF-8")
		             + "&pageNo=" + pageNo
		             +"&returnType=json";
		
		URL reqUrl = new URL(url);
		HttpURLConnection urlConn = (HttpURLConnection)reqUrl.openConnection();
		
		urlConn.setRequestMethod("GET");
		BufferedReader br = new BufferedReader (new InputStreamReader(urlConn.getInputStream()));
	
	String resText = "";
	String line;
	while((line = br.readLine()) != null){
		resText += line;
	}
	br.close();
	urlConn.disconnect();
	
	System.out.println(resText);
	
	return resText;
	}
	
	//air2.do 요청을 받을 메소드
	
	@ResponseBody
	@RequestMapping(value="air2.do", produces="text/xml;charset=UTF-8")
	public String air2Do(String location) throws IOException {
		String url="http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"
				+"?serviceKey=" + SERVICE_KEY
				+ "&sidoName=" + URLEncoder.encode(location, "UTF-8")
				;
		
		URL reqUrl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection)reqUrl.openConnection();
	
	    BufferedReader buf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
	    String resData="";
	    String line;
	    while((line = buf.readLine()) != null){
	    	resData += line;
	    	
	    }
	    
	    buf.close();
	    conn.disconnect();
	    
	    return resData;
	}
	
	//지진해일 긴급대피소 정보 조회 => /shelter.do
	@RequestMapping(value="shelter.do", produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getTsunamiShelter(int rows, @RequestParam("pageNo") int no) throws IOException {
	//1.요청 주소 및 전달 데이터
		String url = "https://apis.data.go.kr/1741000/TsunamiShelter4/getTsunamiShelter4List";
		url += "?ServiceKey=" + SERVICE_KEY;
		url += "&pageNo=" + no;
		url += "&numOfRows=" + rows;
		url += "&type=json";
	//2. 데이터 요청
		URL reqUrl = new URL(url);
		HttpURLConnection conn =(HttpURLConnection)reqUrl.openConnection();
		
	//3. 응답데이터 추출
		BufferedReader buf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String responseData = "";
		String line;
		while ((line = buf.readLine()) != null) {
			responseData += line;
		}
		
		// 4. 스트림 커넥션 객체를 정리
		buf.close();
		conn.disconnect();
		
		return responseData;
		
	}

}









