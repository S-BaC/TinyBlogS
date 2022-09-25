<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TinyBlogS</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
</head>
<body>
	<div class="topBar">
		<h2>TinyBlogS</h2>
		<a style="margin-left:auto;" href="/TinyBlogS/HomePage">Feed</a>
		<a href="/TinyBlogS/MePage">Me</a>
		<a href="/TinyBlogS/LogoutPage">Log out</a>
	</div>
	<hr/>
	
	<form action="/TinyBlogS/updateInfo?type=${type}" method="POST">
		<input style="display:none;" type="text" name="id" value="${id}"/>
		<c:choose>
			<c:when test="${type.equals('editProfile')}">
	        	<h3>EDIT PROFILE</h3>
	        	<div>
		        	<label for="name">Name:</label>
		        	<input type="text" min="1" max="30" name="name"/>
	        	</div>
	        	<div>
		        	<label for="title">Title:</label>
		        	<input type="text" min="1" max="30" name="title"/>
	        	</div>
	        	<div>
		        	<label for="img">Image:</label>
		        	<input type="text" min="1" max="30" name="img"/>
	        	</div>
	        </c:when>
	        
			<c:when test="${type.equals('addBlog')}">
				<h3>ADD BLOG</h3>
				<div>
		        	<label for="title">Title:</label>
		        	<input type="text" min="1" max="30" name="title"/>
	        	</div>
	        	<div>
		        	<label for="text">Text:</label>
		        	<input type="text" min="1" max="255" name="text"/>
	        	</div>
	        	
	        </c:when>
	        
	        <c:when test="${type.equals('editBlog')}">
	        	<h3>EDIT BLOG</h3>
	        	<div>
		        	<label for="title">Title:</label>
		        	<input type="text" min="1" max="30" name="title"/>
	        	</div>
	        	<div>
		        	<label for="text">Text:</label>
		        	<input type="text" min="1" max="255" name="text"/>
	        	</div>
	        	
	        </c:when>
	        
	    </c:choose>
    
        <input class='submitInput' type="submit" value="Submit"/>
    </form>

</body>
</html>