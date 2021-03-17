<jsp:include page="../jsp-components/header.jsp"></jsp:include>
<form action="${pageContext.request.contextPath }/newsServlet" method="get" style="margin-bottom: 200px">
    <div class="login-box">
        <div class="input-section">
            <input class="input-area" type="text" placeholder="name" name="name">
        </div>
        <div class="input-section">
            <input class="input-area" type="text" placeholder="owner" name="owner">
        </div>
        <div class="input-section">
            <input class="input-area" type="text" placeholder="description" name="description">
        </div>
        <div class="input-section">
            <input class="input-area" type="text" placeholder="img_url" name="img_url">
        </div>
        <button class="btn btn-success" id="regbtn" type="submit" name="action" value="add">add</button>
    </div>
</form>

<jsp:include page="../jsp-components/footer.jsp"></jsp:include>