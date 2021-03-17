<%@include file="jsp-components/header.jsp"%>
        <form action="${pageContext.request.contextPath }/login" method="post">
            <h1>${requestScope.message}</h1>
            <p id="logerr"></p>
            <br>
            <div class="login-box">
                <div class="input-section">
                    <i class="far fa-user"></i>
                    <input class="input-area" type="email" placeholder="Username" name="email">
                </div>
                <div class="input-section">
                    <i class="fas fa-lock"></i>
                    <input class="input-area" type="password" placeholder="Password" name="pass">
                </div>
                <button class="btn btn-success" id="login-btn" type="submit">Login</button>
            </div>
        </form>
<jsp:include page="jsp-components/footer.jsp"></jsp:include>