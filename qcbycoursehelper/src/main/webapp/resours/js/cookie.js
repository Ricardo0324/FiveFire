//获取浏览器cookie值
function getCookie(cname){
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) { return c.substring(name.length,c.length); }
    }
    return "";
}

// 实现免登陆   判断是否有cookie值若有则直接跳转界面
function checkCookie()
{
    var id=getCookie("id");
    if(id!="")
    {
        //window.location.href="http://localhost:8080/work/hbu.html";
    }
}





