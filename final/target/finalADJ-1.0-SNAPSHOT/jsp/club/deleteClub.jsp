<jsp:include page="../jsp-components/header.jsp"></jsp:include>
<form action="${pageContext.request.contextPath }/clubServlet" method="get" style="margin-bottom: 200px">
    <div class="login-box">
        <h4 style="color: white">Input club_id to delete</h4>
        <div class="input-section">
            <input class="input-area" type="text" placeholder="id" name="club_id">
        </div>
        <button class="btn btn-success" id="regbtn" type="submit" name="action" value="delete">delete</button>
    </div>
</form>

<jsp:include page="../jsp-components/footer.jsp"></jsp:include>