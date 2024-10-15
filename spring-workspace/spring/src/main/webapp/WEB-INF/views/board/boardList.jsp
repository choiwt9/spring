<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<%-- header.jsp 포함 => 포함된 파일에 선언된 변수를 공유하고자 할 때(include 지시어)--%>
	<%@ include file="../common/header.jsp"%>

	<div class="outer">
		<br>
		<br>
		<div class="innerOuter" style="padding: 5% 10%">
			<h2>게시판</h2>
			<br>

			<%-- 로그인 시에만 글쓰기 버튼 표시 --%>
			<c:if test="${ not empty loginUser }">
				<a href="enrollForm" class="btn btn-secondary" style="float: right">글쓰기</a>
			</c:if>
			<br> <br>
			<table id="boardList" class="table table-hover" align="center">
				<thead>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
					<th>첨부파일</th>
				</thead>

				<tbody>
					<c:forEach var="b" items="${list }">


						<tr>
							<td class="bno">${ b.boardNo }</td>
							<td>${ b.boardTitle }</td> 
							<td>${ b.boardWriter }</td>
							<td>${ b.count }</td>
							<td>${ b.createDate }</td>
							<td><c:if test="${ not empty b.originName }">■</c:if></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
			<br>

			<div id="pagingArea">
				<ul class="pagination">
					<c:choose>
						<c:when test="${pi.currentPage eq 1 }">
							<li class="page-item disabled"><a href="#" class="page-link">Prev</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a
								href="list?cpage=${ pi.currentPage - 1 }" class="page-link">Prev</a>
							</li>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${ pi.startPage }" end="${ pi.endPage }">
						<li class="page-item"><a href="list?cpage=${ i }"
							class="page-link">${ i }</a></li>
					</c:forEach>
					<c:choose>
						<c:when test="${ pi.currentPage eq pi.maxPage }">
							<li class="page-item disabled"><a href="#" class="page-link">Next</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a
								href="list?cpage=${ pi.currentPage + 1 }" class="page-link">Next</a>
							</li>
						</c:otherwise>
					</c:choose>

				</ul>
			</div>

			<br clear="both">

			<form action="" id="searchForm">
				<div class="select">
					<select name="condition" id="" class="custom-select form-select">
						<option value="writer">작성자</option>
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select>
				</div>
				<div class="text">
					<input type="text" class="form-control" name="keyword">
				</div>
				<button class="searchBtn btn btn-secondary">검색</button>
			</form>
			<br>
			<br>
		</div>
		<br>
		<br>
	</div>

	<script type="text/javascript">

$(function(){
	<!-- 게시글 목록의 행을 클릭했을 때 detail 요청하도록 -->
	$("#boardList>tbody>tr").click(function(){
		location.href = 'detail?bno='+ $(this).children(".bno").text();
	});
});


</script>
	<%-- footer.jsp 포함 => 해당 페이지를 포함만 시키고자 할 때(표준액션태그)--%>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>