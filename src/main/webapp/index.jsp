<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/23 0023
  Time: 下午 4:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="http://localhost:8080/statics/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <div class="row">

        <div class="col-sm-8 col-sm-offset-2" >
            <form  class="add_frm" id="frm" action="/a/add" method="post" enctype="multipart/form-data">
                <div class="row m">
                    <div class="col-sm-2">
                        附件：
                    </div>
                    <div class="col-sm-10">
                        <input type="file" class="form-control" id="upFile" name="upFile">
                    </div>
                </div>
                <div class="row m">
                    <div class="col-sm-4 col-sm-offset-4">
                        <button type="submit" class="btn btn-default" id="send">提交</button>
                        <button type="button" class="btn btn-default" id="cancel">取消</button>
                    </div>
                </div>
            </form>
        </div>

    </div>

</div>
</body>
</html>
