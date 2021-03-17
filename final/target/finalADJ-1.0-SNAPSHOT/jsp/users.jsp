<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="jsp-components/header.jsp"></jsp:include>
<script src="jquery-3.5.1.min.js"></script>
<div style="text-align: center">

<div class="input-section">
    <input class="input-area" id="search" type="text" placeholder="search...">
    <button type="submit" class="searchButton bg-dark" id="sbtn">
        <i class="fa fa-search"></i>
    </button>
</div>
<div class="input-section" style="color: white">
    <label><input class="input-area" type="radio" name="searchby" value="byGroup">group</label>
    <label><input class="input-area" type="radio" name="searchby" value="byYear">year</label>
    <label><input class="input-area" type="radio" name="searchby" value="byMajor">major</label>
    <label><input class="input-area" type="radio" name="searchby" value="byName">name</label>
</div>
</div>

<div>
    <div class="container">
        <div class="row" id="errormsg">
            <table class="table table-sm table-dark">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">First Name</th>
                    <th scope="col">Last Name</th>
                    <th scope="col">email</th>
                    <th scope="col">password</th>
                    <th scope="col">group</th>
                    <th scope="col">major</th>
                    <th scope="col">year</th>

                </tr>
                </thead>
                <tbody id = "result">

                </tbody>
            </table>
        </div>
    </div>
</div>
<table class="table table-sm table-dark">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">email</th>
        <th scope="col">password</th>
        <th scope="col">group</th>
        <th scope="col">major</th>
        <th scope="col">year</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${sessionScope.users}">
        <tr>
            <th>${user.id}</th>
            <td>${user.fname}</td>
            <td>${user.lname}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.group}</td>
            <td>${user.major}</td>
            <td>${user.year}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
        <p id="errrr" style="display:none;"></p>
<script type="text/javascript">
    $(document).ready(function (){
        $("#sbtn").on("click", function (){
            event.preventDefault();
            var a = $("input:radio[name='searchby']:checked").val();
            var b =$( "#search" ).val();
            var url = "${pageContext.request.contextPath}/search?&action="+a+"&param="+b;

            $.ajax({
                url: url,
                type: 'GET',  // http method
                accepts: 'application/json; charset=utf-8',
                //contentType: "application/json",
                success: function (data) {
                    if(data != null)
                    {
                        $("#result").html('');
                        $.each(data,function (i,val){
                            var content = '<tr>';
                            $.each(val,function (k,value){
                                if (k!= "club_id" && k!= "event_id" && k!= "news_id" && k!= "role"){
                                content+='<td>'+value+'</td>';
                                }
                            });
                            content+='</tr>';
                            $("#result").append(content);
                        });

                    }
                    else{
                        alert("No result found");
                        $("#errrr").text("No result found");
                        $("#errormsg").hide();
                        $("#errrr").show();

                    }

                },
                error: function (errorData, textStatus, errorMessage) {
                    var msg = (errorData.responseJSON != null) ? errorData.responseJSON.errorMessage : '';
                    $("#errormsg").text('Error: ' + msg + ', ' + errorData.status);

                    $("#errormsg").show();
                }
            });

        });
    });

</script>


<jsp:include page="jsp-components/footer.jsp"></jsp:include>
