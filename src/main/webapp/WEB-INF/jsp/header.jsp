<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<nav class="nav-container">
		<a class="nav-option" href="/app/home">Home</a>
		<a class="nav-option" href="/app/search">Search</a>
		<a class="nav-option" href="/app/booking/view">Bookings</a>
	</nav>
	<nav class="nav-container" style="justify-content: flex-end;">
		
		<a class="nav-option right" style="padding-top: 4%;" href="/app/profile">
			<img class="profile-pic" src='/app/profilePic'/>
		</a>
		<a class="nav-option right" href="/app/logout">Logout</a>
	</nav>
	
</header>
