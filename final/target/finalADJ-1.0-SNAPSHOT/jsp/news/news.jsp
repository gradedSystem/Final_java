<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../jsp-components/header.jsp"></jsp:include><br>
<div class="row">
    <c:forEach var="news" items="${sessionScope.news }">
        <div class="col-md-3 item-box">
            <form class="text-center">
                <input style="display: none;" type="id" name="id" value="">

                <input style="display: none;" type="text" name="image" value="">

                <img style="width: 200px; max-width: 200px; border: 1px solid #192f59; border-radius: 10px;" src="${news.img_url}"><br>

                <h4 style="color: white">${news.name}</h4>
                <input type="name" style="display: none;" name="name" value="${news.name}" readonly><br>

                <input style="width: 200px;" type="text" name="author" value="Author: ${news.owner}" readonly> <br>
                <input style="width: 200px;" type="text" name="id" value="ID: ${news.id}" readonly> <br>
                <input style="width: 200px;" type="text" name="description" value="Description: ${news.description}" readonly> <br>

            </form>
        </div>
    </c:forEach>
</div>


<jsp:include page="../jsp-components/footer.jsp"></jsp:include>