<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title><%=request.getAttribute("actionButton")%></title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/bulma.min.css">
<style type="text/css">
.columns.is-mobile.is-centered {
  margin-top: 10%;
}
</style>
</head>
<body>
	<div class="columns is-mobile is-centered">
		<div class="column is-half">
			<form action="<%=request.getAttribute("action")%>" method="post">
				<div class="field">
					<label class="label">Tên đăng nhập</label>
					<div class="control">
						<input class="input" type="text" name="username">
					</div>
				</div>
				<div class="field">
					<label class="label">Mật khẩu</label>
					<div class="control">
						<input class="input" type="password" name="password">
					</div>
				</div>
				<div class="control has-text-right">
					<input type="submit" class="button is-primary"
						value="<%=request.getAttribute("actionButton")%>">
				</div>
			</form>
		</div>
	</div>

</body>
</html>