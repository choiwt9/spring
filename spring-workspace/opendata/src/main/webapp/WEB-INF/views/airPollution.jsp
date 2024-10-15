<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OpenAPI - 대기오염지수</title>
<!-- 부트스트랩 cdn -->
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
 <!-- jquery cdn -->
 <script
  src="https://code.jquery.com/jquery-3.7.1.min.js"
  integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
  crossorigin="anonymous"></script>
  <!-- 내가 추가한 js 파일 -->
  <!-- 상대경로 -->
  <script src="resources/js/main.js"></script>
  <!-- 절대경로  /opendata/resourses/js/main.js-->
  <!--  <script src="<%=request.getContextPath() %>resources/js/main.js"></script>-->
</head>
<body>
    <div class="p-5" align="center">
        <h2 class="p-2 bg-primary bg-opacity-25">실시간 대기오염 정보</h2>
        <br><br>
        <div class="input-group">
            <span class="input-group-text bg-dark text-white">지역</span>
            <select id="location" class="form-select w-25">
                <option>서울</option>
                <option>인천</option>
                <option>경기</option>
                <option>강원</option>
                <option>부산</option>
            </select>
            <button id="btn1" class="btn btn-dark">대기오염 정보 보기(json)</button>
            <button id="btn2" class="btn btn-waning">대기오염 정보 보기(xml)</button>
        </div>
        <br><br>
        <table id="ap-table" class="table table-hover table-striped text-center">
            <thead>
                <tr class="table-dark">
                    <th>측정소</th>
                    <th>측정일시</th>
                    <th>통합대기환경수치</th>
                    <th>미세먼지농도</th>
                    <th>일산화탄소농도</th>
                    <th>이산화질소농도</th>
                    <th>아황산가스농도</th>
                    <th>오존농도</th>
                </tr>
            </thead>
            <tbody>
             
            </tbody>
        </table>
       
    </div>

</body>
</html>