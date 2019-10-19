<%@page import="entity.Todo"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Công việc</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/bulma.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/lib/fontawesome/font-awesome.min.css">
<style type="text/css">
.undone .fa {
	opacity: 0
}

.undone li:hover {
	cursor: pointer;
}

.undone li:hover .fa {
	opacity: 1
}
</style>
</head>
<body>
	<section class="section">
		<div class="columns is-mobile">
			<div class="column is-4 is-offset-8">
				<span class="subtitle">Xin chào, <%= request.getAttribute("user") %></span>
				<a class="has-text-link" href="logout">Đăng xuất</a>
			</div>
		</div>
		
		<div class="columns is-mobile is-centered">
			<div class="column is-half">
				<form action="<%=request.getContextPath()%>/todo" method="post">
					<div class="field">
						<label class="label">Nội dung</label>
						<div class="control">
							<input class="input" type="text" name="content">
						</div>
					</div>
					<div class="control has-text-right">
						<input type="submit" class="button is-primary" value="Thêm">
					</div>
				</form>
			</div>
		</div>

		<div class="columns">
			<div class="column is-6">
				<div class="box">
					<div class="content undone">
						<p>Chưa hoàn thành</p>
						<ul>
							<%
								List<Todo> undone = (List<Todo>) request.getAttribute("undone");
								List<Todo> done = (List<Todo>) request.getAttribute("done");

								if (null != undone)
									for (Todo job : undone) {
							%>
							<li data-id="<%=job.getId()%>"><span><%=job.getContent()%></span>
								<span class="icon"> <i class="fa fa-check"
									aria-hidden="true"></i>
							</span></li>
							<%
								}
							%>
						</ul>
					</div>
				</div>
			</div>
			<div class="column is-6">
				<div class="box">
					<div class="content done">
						<p>Đã hoàn thành</p>
						<ul>
							<%
								if (null != done)
									for (Todo job : done) {
							%>
							<li data-id="<%=job.getId()%>"><span><%=job.getContent()%></span>
								<span class="icon"> <i class="fa fa-check"
									aria-hidden="true"></i>
							</span></li>
							<%
								}
							%>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript">
		window.addEventListener('load', (e) => {
			
			const undone = document.querySelectorAll('.undone li');
			const done = document.querySelector('.done ul');
			
			undone.forEach(function(el) {
				el.addEventListener('click', function(e) {
					
					fetch('/webapp/todo?id=' + this.getAttribute('data-id'), {
						method: 'PUT',
					});
					
					const job = this.cloneNode(true);
					this.remove();
					done.appendChild(job);
				}, false)
			});
			
		}, false);
	</script>
</body>
</html>