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
  <%-- <jsp:include page="../common/header.jsp" />--%>  
  <%@ include file = "../common/header.jsp"%>

    <div class="outer">
        <br><br>
        <div class="inner-area">
            
            <h2>회원가입</h2>
            <br>
            
            <%-- 상대경로일때  action="insert"--%>
            <form action="<%=contextPath %>/member/insert" method="post">

                <div class="form-group">
                    <label for="userId">* ID </label>
                    <input type="text" class="form-control mb-3" name="userId" id="userId" placeholder="Enter ID.." required>

                    <label for="userPwd">* Password </label>
                    <input type="password" class="form-control mb-3" name="userPwd" id="userPwd" placeholder="Enter Password.." required>

                    <label for="checkPwd">* Password Check </label>
                    <input type="password" class="form-control mb-3" id="checkPwd" placeholder="Enter Password.." required>
                    
                    <label for="userName">* Name </label>
                    <input type="text" class="form-control mb-3" name="userName" id="userName" placeholder="Enter Name.." required>

                    <label for="email"> &nbsp; Email </label>
                    <input type="email" class="form-control mb-3" name="email" id="email" placeholder="Enter Email..">  
                    
                    <label for="age"> &nbsp; Age </label>
                    <input type="number" class="form-control mb-3" name="age" id="age" placeholder="Enter Age..">  
                    
                    <label for="phone"> &nbsp; Phone </label>
                    <input type="tel" class="form-control mb-3" name="phone" id="phone" placeholder="Enter Phone(-제외)..">  
                    
                    <label for="address"> &nbsp; Address </label>
                    <input type="text" class="form-control mb-3" name="address" id="address" placeholder="Enter Address..">

                    <label for=""> &nbsp; Gender</label> &nbsp;&nbsp;
                    <input type="radio" class="mb-3" name="" id="Male" value="M">
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" class="mb-3" name="" id="Female" value="F">
                    <label for="Female">여자</label><br>

                </div>
                <br>
                <div class="btns"  align="center">
                    <button type="submit" class="btn btn-primary">회원가입</button>
                    <button type="reset" class="btn btn-danger">초기화</button>
                </div>
            </form>
        </div>
        <br><br>
    </div>

    <%-- footer --%>
    <jsp:include page="../common/footer.jsp" />
</body>
</html>