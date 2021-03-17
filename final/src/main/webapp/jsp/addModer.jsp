<jsp:include page="jsp-components/header.jsp"></jsp:include>
<form action="${pageContext.request.contextPath }/moderServlet" method="get" style="margin-bottom: 200px">
    <div class="login-box">
        <div class="input-section">
            <input class="input-area" type="text" placeholder="user_id" name="user_id">
        </div>
        <div class="input-section">
            <input class="input-area" type="text" placeholder="club,event or news id" name="id">
        </div>
        <button class="btn btn-success" type="submit" name="action" value="clubModer">add to Club moders</button><br>
        <button class="btn btn-success" type="submit" name="action" value="eventModer">add to Event moders</button><br>
        <button class="btn btn-success" type="submit" name="action" value="newsModer">add to News moders</button>
    </div>
</form>

<jsp:include page="jsp-components/footer.jsp"></jsp:include>