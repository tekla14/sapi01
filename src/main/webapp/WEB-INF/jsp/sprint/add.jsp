<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="/static/js/story.form.js"></script>
</head>
<body>
    <h1><spring:message code="label.sprint.add.page.title"/></h1>
    <div class="well page-content">
        <form:form action="/sprint/add" commandName="sprint" method="POST" enctype="utf8">
            <div id="control-group-title" class="control-group">
                <label for="sprint-title"><spring:message code="label.sprint.title"/>:</label>

                <div class="controls">
                    <form:input id="sprint-title" path="title"/>
                    <form:errors id="error-title" path="title" cssClass="help-inline"/>
                </div>
            </div>
            
            <div class="action-buttons">
                <a href="/" class="btn"><spring:message code="label.cancel"/></a>
                <button id="add-sprint-button" type="submit" class="btn btn-primary"><spring:message
                        code="label.add.sprint.button"/></button>
            </div>
        </form:form>
    </div>
</body>
</html>