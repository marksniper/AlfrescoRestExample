<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accedi</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous"/>
</head>
<body>
<div class="container">
    <form class="form-signin" action="<c:url value="/accedi"/>" method="post" >
        <h2 class="form-signin-heading">Accedi alle risorse</h2>
        <c:if test="${param.error != null}">
            <div class="alert alert-danger" role="alert">
                Username e password non validi.
            </div>
        </c:if>
        <c:if test="${param.logout != null}">
            <div class="alert alert-success" role="alert">
                Ti sei disconesso.
            </div>
        </c:if>
        <p>
            <label for="username" class="sr-only">Username</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Username" required
                   autofocus autocomplete="username"/>
        </p>
        <p>
            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required autocomplete="password"/>
        </p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
    </form>
</div>
</body>
</html>