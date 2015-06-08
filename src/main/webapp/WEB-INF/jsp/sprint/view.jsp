<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title></title>
    <script type="text/javascript" src="/static/js/sprint.view.js"></script>
</head>
<body>
    <div id="sprint-id" class="hidden">${sprint.id}</div>
    <div class="well page-content">
        <h2 id="sprint-title"><c:out value="${sprint.title}"/></h2>
        <div>
            <p><c:out value="${sprint.description}"/></p>
        </div>
        <div id="story-list" class="page-content">
        <div>
        	<a href="/sprint/add" id="add-button" class="btn btn-primary"><spring:message code="label.sprint.add.story.title"/></a>
    	</div>
        <c:choose>
            <c:when test="${empty stories}">
                <p><spring:message code="label.story.list.empty"/></p>
            </c:when>
            <c:otherwise>
                <c:forEach items="${ stories}" var="story">
                    <div class="well well-small">
                        <a href="/story/${story.id}"><c:out value="${story.title}"/></a>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    	</div>
    	<div id="comment-list" class="page-content">
    	<div>
        	<a href="/sprint/add" id="add-button" class="btn btn-primary"><spring:message code="label.sprint.add.comment.title"/></a>
    	</div>
        
        <c:choose>
            <c:when test="${empty comments}">
                <p><spring:message code="label.comment.list.empty"/></p>
            </c:when>
            <c:otherwise>
                <c:forEach items="${ comments}" var="comment">
                    <div class="well well-small">
                        <a href="/comment/${comment.id}"><c:out value="${comment.title}"/></a>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
    </div>
    
    <div>
    </div>
    
</body>
</html>