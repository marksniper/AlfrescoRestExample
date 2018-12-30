<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Marco
  Date: 23/09/2018
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="res/js/other/jquery-3.3.1.min.js"></script>
    <script src="res/bootstrap/bootstrap.min.js" type="text/javascript"></script>

    <script src="res/js/login.js" type="text/javascript"></script>
    <script src="res/js/infoNode.js" type="text/javascript"></script>
    <script src="res/js/uploadView.js" type="text/javascript"></script>
    <script src="res/js/ServiceIndex.js" type="text/javascript"></script>
    <script src="res/js/useServiceIndex.js" type="text/javascript"></script>

    <link href="res/css/InfoCartelle.css" rel="stylesheet" type="text/css"/>
    <link href="res/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="res/css/popup.css" rel="stylesheet" type="text/css"/>
    <link href="res/css/uploadView.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<h1> <c:out value="${pageContext.request.remoteUser}"/></h1>

<div id="user-name-label" type="text">

</div>
<section>
    <nav>
        <div id="fileFolder">

        </div>

    </nav>
    <article>

</section>
<a href="<c:url value="/semantic"/>">Vai a semantic</a>
<form action="<c:url value="/logout"/>" method="post">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <input type="submit" value="Logout">
</form>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header1">
                <span class="close">&times;</span>
                <h2 id="nomedelnodo"></h2>
            </div>
            <div class="modal-body1">
                <p id="informazioniNodo"></p>
            </div>
            <div class="modal-footer1">

                <div class="row m-b-3">
                    <div class="col-sm-6 offset-sm-3" id="insersciFile">


                    </div>

                </div>
            </div>
        </div>
    </div>

    <input type="hidden" id="idNodoPerAltreOperazioni" value="">
    <input type="hidden" id="checkPostOperationForUpload" value="">
</body>
</html>
