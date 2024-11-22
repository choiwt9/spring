<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%-- header --%>
   <%--  <jsp:include page="../common/header.jsp" />--%>
  <%@ include file = "../common/header.jsp"%>
    <div class="outer">
        <br><br>
        <div class="inner-area">
            <h2>게시글 수정하기</h2>
            <br><br>

            <form id="updateForm" method="post" action="update" enctype="multipart/form-data">
               <input type="hidden" name="boardNo" value="${ board.boardNo }">
                <table align="center" class="table">
                    <tr>
                        <th width="100"><label for="title">제목</label></th>
                        <td>
                            <input type="text" name="boardTitle" id="title" class="form-control" value="${ board.boarTitle }" required />
                        </td>
                    </tr>
                    <tr>
                        <th><label for="writer">작성자</label></th>
                        <td>
                            <input type="text" name="boardWriter" id="writer" class="form-control" value="${ board.boarWriter }" readonly />
                        </td>
                    </tr>
                    <tr>
                        <th><label for="upfile">첨부파일</label></th>
                        <td>
                            <input type="file" name="upfile" id="upfile" class="form-control-file border"><br>
                            첨부 파일 :
                            <c:choose>
                            <c:when test="${not empty board.originName }">
                            <a href="<%= contextPath %>/${board.changeName}" download="${ board.originName }">${ board.originName }</a>
                            </c:when>
                            <c:otherwise>
                             없음
                            </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2"><label for="content">내용</label></th>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <textarea name="boardContent" id="content" class="form-control" rows="10" style="resize:none;" required>내용입니당@@@@@</textarea>
                        </td>
                    </tr>
                </table>
                <br>

                <div align="center">
                    <!-- 작성자와 로그인한 계정이 동일한 경우만 표시 -->
                    <button type="submit" class="btn btn-primary">수정</button>
                    <button type="button" class="btn btn-danger" onclick="history.go(-1);">이전</button>
                </div>
                <br><br>
            </form>
        </div>


    </div>

    <%-- footer --%>
    <jsp:include page="../common/footer.jsp" />    
    
    
    
</body>
</html>