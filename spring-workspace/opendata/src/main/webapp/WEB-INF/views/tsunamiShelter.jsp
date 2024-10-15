<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>OpenAPI - 지진해일 긴급대피소</title>

    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    
    <!-- jQuery CDN -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

    <!--shelter.js파일 추가  -->
    <script src="resources/js/shelter.js"></script>
</head>
<body>
	<a href="<%= request.getContextPath() %>" class="btn btn-light mt-5 ms-5">홈으로</a>
    <div class="p-5" align="center">
        <h2>지진해일 긴급대피장소</h2>
        <br><br>
        <div class="d-flex justify-content-between">
	        <div class="fw-bold">
	        	<span id="currPage">1</span>
	        	/
	        	<span id="maxPage">0</span>
	        </div>
        	<div class="input-group w-25">
	            <select id="rows" class="form-select">
	                <option value="10">10건씩</option>
	                <option value="30">30건씩</option>
	                <option value="50">50건씩</option>
	                <option value="100">100건씩</option>
	            </select>
            	<button id="btn1" class="btn btn-danger">정보 보기</button>
            </div>		
        </div>
        <br><br>
        <table id="shelter-list" class="table table-hover table-striped text-center">
            <thead>
                <tr class="table-success">
                    <th>대피소명</th>
                    <th>주소</th>
                    <th>최대 수용인원</th>
                    <th>타입</th>
                    <th>연락처</th>
					<th>관리기관</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
        <nav aria-label="Page navigation example">
		  <ul id="page-area" class="pagination justify-content-center"></ul>
		</nav>
    </div>
</body>
</html>