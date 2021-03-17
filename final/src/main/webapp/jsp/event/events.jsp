<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../jsp-components/header.jsp"></jsp:include><br>
<div class="row">
    <c:forEach var="event" items="${sessionScope.events }">
        <div class="col-md-3 item-box">
            <form class="text-center">
                <input style="display: none;" type="id" name="id" value="">

                <input style="display: none;" type="text" name="image" value="">

                <img style="width: 200px; max-width: 200px; border: 1px solid #192f59; border-radius: 10px;" src="${event.img_url}"><br>

                <h4 style="color: white">${event.name}</h4>
                <input type="name" style="display: none;" name="name" value="${event.name}" readonly><br>

                <input style="width: 200px;" type="text" name="author" value="Owner: ${event.owner}" readonly> <br>
                <input style="width: 200px;" type="text" name="id" value="ID: ${event.id}" readonly> <br>
                <input style="width: 200px;" type="text" name="description" value="Description: ${event.description}" readonly> <br>

            </form>
        </div>
    </c:forEach>
</div>


<jsp:include page="../jsp-components/footer.jsp"></jsp:include>