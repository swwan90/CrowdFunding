<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <!-- http://localhost:8080/atcrowdfunding02-admin-webui/test/ssm.html -->
    <!--
        base 标签必须写在 head 标签内部
         base 标签必须在所有“带具体路径”的标签的前面
         serverName 部分 EL 表达式和 serverPort 部分 EL 表达式之间必须写“:”
         serverPort部分EL表达式和contextPath部分EL表达式之间绝对不能写“/”
         原因：contextPath 部分 EL 表达式本身就是“/”开头
         如果多写一个“/”会干扰 Cookie 的工作机制
         serverPort 部分 EL 表达式后面必须写“/”
    -->
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath}/"/>
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"/>
    <script type="text/javascript">
        $(function () {

            $("btn1").click(function () {

                $.ajax({
                    "url":"send/array/one.html",        // 请求目标资源的位置
                    "type":"post",                      // 请求方式
                    "data":{                            // 要发送的请求参数
                        "array":[5,8,12]
                    },
                    "dataType":"text",                  // 如何对待服务器返回的数据
                    "success":function (response) {     // 服务器端成功处理请求后调用的回调方法
                        alert(response);
                    },
                    "error":function (response) {       // 服务器端处理请求失败后调用的回调方法

                        alert(response);
                    }
                });
            });

            $("btn2").click(function () {

                $.ajax({
                    "url":"send/array/two.html",
                    "type":"post",
                    "data":{
                        "array[0]":5,
                        "array[1]":8,
                        "arrray[2]":12
                    },
                    "dataType":"text",
                    "success":function (response) {
                        alert(response);
                    },
                    "error":function (response) {
                        alert(response);

                    }
                });
            });

            $("btn3").click(function () {

                // 准备好要发送到服务器的数组
                var array=[5,8,12];
                console.log(array.length);

                // 将 JSON 数组转换成 JSON 字符串
                var requestBody = JSON.stringify(array);
                console.log(requestBody.length)

                $.ajax({

                    "url":"send/array/three.html",
                    "type":"post",
                    "data":requestBody,
                    "contentType":"application/json;charset=UTF-8",
                    "dataType":"text",
                    "success":function (response) {
                        alert(response);

                    },
                    "error":function (response) {
                        alert(response);

                    }
                });
            });
        });

    </script>

</head>
<body>

    <a href="test/ssm.html">测试SSM整合环境</a>

    <br/>
    <br/>

    <button id="btn1">Send [5,8,12] One</button>

    <br/>
    <br/>

    <button id="btn2">Send [5,8,12] Two</button>

    <br/>
    <br/>

    <button id="btn3">Send [5,8,12] Three</button>

</body>
</html>