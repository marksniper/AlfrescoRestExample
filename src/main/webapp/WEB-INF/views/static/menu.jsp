
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="res/js/activeMenu.js" type="text/javascript"></script>
<div class="ui secondary  menu">
    <a class="item" id="1">
        Home
    </a>
    <a class="item" id="2">
        Messages
    </a>
    <a class="item" id="3">
        Friends
    </a>
    <a href="<c:url value="/"/>" class="item" id="4">
        First Index
    </a>
    <div class="right menu">

        <form class="ui form" action="<c:url value="/logout"/>" method="post">

            <button class="ui inverted red button" type="submit">
            <i class="power off icon"></i>
                Logout
            </button>
        </form>

    </div>
</div>