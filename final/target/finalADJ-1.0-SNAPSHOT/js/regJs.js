$(document).ready(function() {
    $("#emailInput").focus(function () {
        $(this).keyup(function () {
            $('#checkemail').text("Loading...");
            $("#checkemail").addClass("text-white");
        })
    });
    ////////////////////////////////////////////////////////////
    $("#regbtn").prop('disabled', true);
    $("#emailInput").blur(function () {
        $.ajax({
            url: "http://localhost:8080/finalADJ_war_exploded/register?&action=checkEmail&email=" + $("#emailInput").val(),
            type: 'GET',
            success: function (data) {
                if (data.message == "empty") {
                    $("#checkemail").text("Fill email input");
                    $("#checkemail").addClass("text-white");
                    $("#regbtn").prop('disabled', true);
                } else if (data.message == "success") {
                    $("#checkemail").text("This email is free");
                    $("#checkemail").addClass("text-white");
                    $("#regbtn").prop('disabled', false);
                } else {
                    $("#checkemail").text("Error: " + data.message);
                    $("#checkemail").addClass("text-white");
                    $("#regbtn").prop('disabled', true);
                }
            }
        });
    });


    $("#yearInput").focus(function () {
        $(this).keyup(function () {
            $('#checkyear').text("Loading...");
            $("#checkyear").addClass("text-white");
        })
    });
    ////////////////////////////////////////////////////////////
    $("#regbtn").prop('disabled', true);
    $("#yearInput").blur(function () {
        $.ajax({
            url: "http://localhost:8080/finalADJ_war_exploded/register?&action=checkYear&year=" + $("#yearInput").val(),
            type: 'GET',
            success: function (data) {
                if (data.yearMessage == "empty") {
                    $("#checkyear").addClass("text-white");
                    $("#checkyear").text("Fill year input");
                    $("#regbtn").prop('disabled', true);
                } else if (data.yearMessage == "success") {
                    $("#checkyear").addClass("text-white");
                    $("#checkyear").text("Correct year");
                    $("#regbtn").prop('disabled', false);
                } else {
                    $("#checkyear").addClass("text-white");
                    $("#checkyear").text("Error: " + data.message);
                    $("#regbtn").prop('disabled', true);
                }
            }
        });
    });
});