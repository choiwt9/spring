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
    <jsp:include page="../common/header.jsp" />

    <div class="outer">
        <br><br>
        <div class="inner-area">
            <h2>게시글 작성하기</h2>
            <br>

            <form action="insert" enctype="multipart/form-data" id="enrollForm" method="post">

                <table align="center" class="table">
                    <tr>
                        <th>제목</th>
                        <td>
                            <input type="text" name="boardTitle" id="title" class="form-control" required>
                        </td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td>
                            <input type="text" name="boardWriter" id="writer" value="${ loginUser.userId }" class="form-control" readonly>
                        </td>
                    </tr>
                    <tr>
                        <th>첨부파일</th>
                        <td>
                            <input type="file" name="upfile" id="upfile" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">내용</th>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <textarea name="boardContent" id="content" cols="30" rows="10" class="form-control" style="resize: none;" required></textarea>
                        </td>
                    </tr>
                </table>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary">등록하기</button>
                    <button type="button" class="btn btn-danger">취소하기</button>
                </div>
            </form>
        </div>


    </div>

    <%-- footer --%>
    <jsp:include page="../common/footer.jsp" />
</body>
</html>