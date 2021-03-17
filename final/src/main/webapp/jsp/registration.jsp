<%@include file="jsp-components/header.jsp"%>
<form action="${pageContext.request.contextPath }/register" method="post" style="margin-bottom: 200px">
    <div class="login-box">
        <div class="input-section">
            <p id="checkemail"></p>
            <input class="input-area" type="email" id="emailInput" placeholder="email" name="email">
        </div>
        <div class="input-section">
            <input class="input-area" type="password" placeholder="Password" name="pass">
        </div>
        <div class="input-section">
            <input class="input-area" type="text" placeholder="first name" name="fname">
        </div>
        <div class="input-section">
            <input class="input-area" type="text" placeholder="last name" name="lname">
        </div>
        <div class="input-section">
            <p id="checkyear"></p>
            <input class="input-area" id="yearInput" type="text" placeholder="year" name="year">
        </div>
        <div class="input-section">
            <input class="input-area" type="text" placeholder="major" name="major">
        </div>
        <div class="input-section">
            <input class="input-area" type="text" placeholder="group" name="group">
        </div>
        <button class="btn btn-success" id="regbtn" type="submit">Register</button>
    </div>
</form>
<script src="${pageContext.request.contextPath}/js/regJs.js"></script>
<jsp:include page="jsp-components/footer.jsp"></jsp:include>
