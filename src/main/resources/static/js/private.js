Date.prototype.format = function (format)
{
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(),    //day
        "h+": this.getHours(),   //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
        (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o) if (new RegExp("(" + k + ")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length == 1 ? o[k] :
                ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}
$(function ()
{
    $("#loginButton").click(function ()
    {
        login();
    });

    $("#loginYzm").keyup(function (event)
    {
        if (event.which == 13)
            login();
    });

    $("#registerButton").click(function ()
    {
        var uname = $("#registerUname").val();
        var upwd = $("#registerUpwd").val();
        var confirmPwd = $("#registerconfirmUpwd").val();
        var inviteCode = $("#inviteCode").val();
        var yzm = $("#registerYzm").val();

        var flag = true;

        if (uname.length > 16)
        {
            $("#registerUname+span").html("X").css("background-color", "red");
            flag = false;
        }
        else
            $("#registerUname+span").html("").css("background-color", "#EEEEEE");

        if ((upwd.length > 16) || (upwd.length < 6))
        {
            $("#registerUpwd+span").html("X").css("background-color", "red");
            flag = false;
        }
        else
            $("#registerUpwd+span").html("").css("background-color", "#EEEEEE");

        if (upwd != confirmPwd)
        {
            $("#registerconfirmUpwd+span").html("X").css("background-color", "red");
            flag = false;
        }
        else
            $("#registerconfirmUpwd+span").html("").css("background-color", "#EEEEEE");

        if (inviteCode == "")
        {
            $("#inviteCode+span").html("X").css("background-color", "red");
            flag = false;
        }
        else
            $("#inviteCode+span").html("").css("background-color", "#EEEEEE");

        if (yzm == "")
        {
            $("#registerYzm+span").html("X").css("background-color", "red");
            flag = false;
        }
        else
            $("#registerYzm+span").html("").css("background-color", "#EEEEEE");


        if (flag)
        {
            var data = {"uname": uname, "upwd": upwd, "icode": inviteCode, "yzm": yzm};
            $.post("/register.do", data, function (data)
            {
                alert(data);
                if (data == "注册成功")
                    location.reload()
            });
        }
    });
    $(".switchCode").click(function ()
    {
        alert("瞎子上什么论坛")
        $(".yzmImg").attr("src", "/yzm.do?c=" + new Date().getMilliseconds());
    })
    $("#logouot").click(function ()
    {
        $.get("/logout.do", {}, function (data)
        {
            alert(data);
            if (data == "退出成功")
                location.reload()
        });
    });

    $("#netdisk").click(function ()
    {
        window.location.href = '/netdisk.do';
    });

    $("#person").click(function ()
    {
        window.location.href = '/person.do';
    });


    $("#sendPostButton").click(function ()
    {
        var ptitle = $("#sendPostTitle").val();
        var pbody = $("#sendPostBody").val();
        if ((ptitle.length > 0 && ptitle.length <= 30) && (pbody.length > 0 && pbody.length < 1000))
        {
            var data = {"ptitle": ptitle, "pbody": pbody};
            $.post("/sendPost.do", data, function (data)
            {
                alert(data);
                if (data == "发送成功")
                    location.reload()
            });
        }
        else
            alert("注意字数");
    });
    $("#sendReply").click(function ()
    {
        var replyMessage = $("#replyMessage").val();
        var pid = $("#pid").val();
        if (replyMessage.length > 0 && replyMessage.length <= 1000)
        {
            var data = {"replymessage": replyMessage, "post.pid": pid};
            $.post("/reply.do", data, function (data)
            {
                alert(data);
                if (data == "回帖成功")
                    location.reload()
            });
        }
        else
            alert("注意字数");
    })

    $("#deleteReply").click(function ()
    {
        var url = $(this).parent().attr("href");
        $.get(url, function (data)
        {
            alert(data)
            if (data == "删除成功")
                location.reload()
        })
    });
    $("#generateCode").click(function ()
    {
        $.get("/generateCode.do", function (data)
        {
            var txt = "<tr><td>" + data.icode + "</td><td>" + new Date(data.icreatetime).format("yyyy-MM-dd hh:mm:ss") + "</td><td>未激活</td><td></td><td><a href='/deleteCode/" + data.icode + "'>删除</a></td></tr>";
            $("#codeList").append(txt);
        }, "json");
    });

    $("#updatePwd").click(function ()
    {
        var personNewPwd = $("#personNewPwd").val();
        var personConfirmPwd = $("#personConfirmPwd").val();
        if (personNewPwd.length > 6 && personNewPwd.length <= 16)
            if (personNewPwd == personConfirmPwd)
                $("#updateForm").submit();
            else
            {
                alert("两次输入的密码不相符");
                return false;
            }
        else
        {
            alert("新密码长度长度(6,16]")
            return false;
        }

    });

});

function banUser(uid)
{
    $.get("/ban/" + uid, function (data)
    {
        alert(data)
        if (data == "禁言成功")
            location.reload();
    });
}

function unbanUser(uid)
{
    $.get("/unban/" + uid, function (data)
    {
        alert(data)
        if (data == "解禁成功")
            location.reload();
    });
}

function login()
{
    var uname = $("#loginUname").val();
    var upwd = $("#loginUpwd").val();
    var yzm = $("#loginYzm").val();

    if (uname == "" || upwd == "" || yzm == "")
        return;
    else
    {
        var data = {"uname": uname, "upwd": upwd, "yzm": yzm};
        $.post("/login.do", data, function (data)
        {
            alert(data);
            if (data == "登录成功")
                location.reload();
            else
            {
                $(".yzmImg").attr("src", "/yzm.do?c=" + new Date().getMilliseconds());
                $("#loginYzm").val("");
            }
        });
    }
}

/*]]>*/