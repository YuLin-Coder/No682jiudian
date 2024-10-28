<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>大酒店</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript"
		src="../resources/home/js/jquery-1.9.0.min.js"></script>
	<script type="text/javascript" src="../resources/home/js/login.js"></script>
	<link href="../resources/home/css/login2.css" rel="stylesheet"
		type="text/css" />
</head>
<body style="background: url(../resources/home/images/shouye1.png)">
	<h1>
		琉璃汀大酒店<sup><%--<span style="color: red">欢迎*</span>--%><a href="index2" style="position: relative;">进入首页</a></sup>
	</h1>

	<div class="login" style="margin-top: 50px;">

		<div class="header">
			<div class="switch" id="switch">
				<a class="switch_btn_focus" id="switch_qlogin"
					href="javascript:void(0);" tabindex="7">快速登录</a> <a
					class="switch_btn" id="switch_login" href="javascript:void(0);"
					tabindex="8">快速注册</a>
				<div class="switch_bottom" id="switch_bottom"
					style="position: absolute; width: 64px; left: 0px;"></div>
			</div>
		</div>


		<div class="web_qr_login" id="web_qr_login"
			style="display: block; height: 380px;">

			<!--登录-->
			<div class="web_login" id="web_login">


				<div class="login-box">


					<div class="login_form">
						<form name="loginform" accept-charset="utf-8" id="login_form"
							class="loginForm">
							<input type="hidden" name="did" value="0" /> <input
								type="hidden" name="to" value="log" />
							<div class="uinArea" id="uinArea">
								<label class="input-tips" for="u">帐号：</label>
								<div class="inputOuter" id="uArea">

									<input type="text" id="u" name="username" class="inputstyle" />
								</div>
							</div>
							<div class="pwdArea" id="pwdArea">
								<label class="input-tips" for="p">密码：</label>
								<div class="inputOuter" id="pArea">

									<input type="password" id="p" name="password"
										class="inputstyle" />
								</div>
							</div>

							<div class="vcodeArea" id="vcodeArea">
								<label class="input-tips" for="p">验证：</label>
								<div class="inputOuter" id="pArea">

									<input id="vcode" maxlength="4" type="text" class="inputstyle"
										placeholder="请输入验证码" style="width: 50%" /> <img
										id="cpacha-img"
										src="../system/get_cpacha?vl=4&w=180&h=38&type=accountLoginCpacha"
										onclick="changeVcode()" class="code"
										style="cursor: pointer; position: absolute;" />
								</div>
							</div>

							<div style="padding-left: 50px; margin-top: 20px;">
								<input type="button" value="登 录"
									style="width: 120px; margin-top: 40px;" id="button_blue"
									class="button_blue" /><input type="button" value="忘记密码？"
									style="width: 100px; margin-top: 40px; background: white; color: blue; border-bottom: 3px solid #ffffff;"
									class="button_blue" id="button_wj" />
							</div>
						</form>
					</div>

				</div>

			</div>
			<!--登录end-->
		</div>

		<!--注册-->
		<div class="qlogin" id="qlogin" style="display: none; width: 800px">

			<div class="web_login">
				<form name="form2" id="regUser" accept-charset="utf-8">
					<input type="hidden" name="to" value="reg" /> <input type="hidden"
						name="did" value="0" />
					<ul class="reg_form" id="reg-ul">
						<li id="sd"><label for="user" class="input-tips2">用户名：</label>
							<div class="inputOuter2">
								<input type="text" id="uname" name="name" maxlength="16"
									class="inputstyle2"   placeholder="请输入3-9位字符" style="color: gray"/><span id="gh" style="color:red"></span>
							</div></li>

						<li><label for="passwd" class="input-tips2">密码：</label>
							<div class="inputOuter2">
								<input type="password" id="upwd" name="password" maxlength="16"
									class="inputstyle2" placeholder="请输入6-13位字符" style="color: gray"/>
								<span id="gs" style="color:red"></span>
							</div></li>
						<li><label for="passwd2" class="input-tips2">确认密码：</label>
							<div class="inputOuter2">
								<input type="password" id="upwd2" name="" maxlength="16"
									class="inputstyle2"  placeholder="请确认密码" style="color: gray" /><span id="gk" style="color:red"></span>
							</div></li>

						<li><label for="qq" class="input-tips2">手机：</label>
							<div class="inputOuter2">

								<input type="text" id="uphone" name="mobile" class="inputstyle2"  placeholder="请输入正确手机号" style="color: gray"/><span id="ga" style="color:red"></span>
							</div></li>
						<!--onkeyup="value=value.replace(/[^\d]/g,'')"
									title='手机号格式不正确,11位数字符' name="mobile" maxlength="13"-->

						<li>
							<div class="inputArea">
								<input type="button" id="btn_reg"
									style="margin-top: 10px; margin-left: 85px;"
									class="button_blue" value="同意协议并注册" /> <a href="#"
									class="zcxy" target="_blank">有账号去登录</a>
							</div>

						</li>
						<div class="cl"></div>
					</ul>
				</form>


			</div>


		</div>
		<!--注册end-->
	</div>
	<div class="jianyi"></div>
	<script>

		$(function (){


			$("#uname").blur(function(){

				checker()
			})
			function checker(){

				var vals=$("#uname").val()
				var chec=new RegExp("^\\w{3,9}$")
				var res=chec.test(vals)

				if (res==false){
					$("#gh").show().html("用户名格式不正确，请输入三到九位字符！")
				}else {
					$("#gh").show().html("")
				}
				// var re=/^\w{3,9}$/
				// if(vals==""||vals==null){
				// 	return false
				// 	$("#gh").show().html("用户名不能为空！")
				// }


				// if(re.test(vals)){
				//
				// 	$("#gh").hide()
				// 	return true
				// }else{
				// 	$("#gh").show().html("用户名格式错误！应为3~9个字符之间之间")
				// 	return false
				// }

			}

			$("#upwd").blur(function(){


				checker1()
			})
			function checker1(){

				var vals=$("#upwd").val()
				var chec=new RegExp("^\\w{6,13}$")
				var res=chec.test(vals)
				if (res==false){
					$("#gs").show().html("密码格式不正确，请输入6到13位字符！")
				}else {
					$("#gs").show().html("")
				}
				// var re=/^\w{6,13}$/
				// if(vals==""||vals==null){
				// 	return false
				// 	$("#gs").show().html("密码不能为空！")
				// }
				//
				// if(re.test(vals)){
				// 	$("#gs").hide()
				// 	return true
				// }else{
				// 	$("#gs").html("密码格式错误！应为6~13个字符之间之间")
				// 	return false
				// }

			}

			$("#upwd2").blur(function(){
				checker2()
			})
			function checker2(){

				var vals1=$("#upwd2").val()
				var vals2=$("#upwd").val()


				if(vals1!=vals2){

					$("#gk").show().html("两次密码不一致！！")
				}else{

					$("#gk").hide()
				}



			}

			$("#uphone").blur(function(){


				checker3()
			})
			function checker3(){

				var vals=$("#uphone").val()
				var re=/^\w{11,11}$/


				if(re.test(vals)){
					$("#ga").hide()

				}else{
					$("#ga").show().html("手机号应为11位！")

				}



			}



		})
		function changeVcode() {
			$("#cpacha-img").attr(
					"src",
					'../system/get_cpacha?vl=4&w=173&h=33&type=accountLoginCpacha&t='
							+ new Date().getTime());
		}

		$(document).ready(function() {
			$("#button_blue").click(function() {
				var name = $("#u").val();
				var password = $("#p").val();
				var vcode = $("#vcode").val();
				if (name == '' || password == '' || vcode == '') {
					alert('请填写完成信息再提交!');
					return;
				}
				$.ajax({
					url : 'login',
					type : 'post',
					dataType : 'json',
					data : {
						name : name,
						password : password,
						vcode : vcode
					},
					success : function(data) {
						if (data.type == 'success') {
							window.location.href = 'index2';
						} else {
							alert(data.msg)
							changeVcode();
						}
					}
				});
			})
		});

		$('#btn_reg').click(function() {
			//表单序列化，获得所有的用户输入
			/*if ($("#upwd").val() != $("#upwd2").val()) {
				alert('两次密码不一致！');
				return;
			}*/

			var data = $('#regUser').serialize();

			//异步提交请求数据
			$.ajax({
				type : 'POST',
				dataType : 'json',
				url : 'reg',
				data : data,
				success : function(result) {
					//console.log(result);
					if (result.type == 'success') {
						alert('注册成功！');
						location.href = 'login2';
					} else {
						alert(result.msg)
					}
				}
			});
		})

		$('#button_wj').click(function() {
			var NameAndMobile = prompt("请输入您的账号于预留手机号，用#隔开", ""); //将输入的内容赋给变量 name ，
			var Email = prompt("请输入您要重置密码要发送的邮箱", "");

			//这里需要注意的是，prompt有两个参数，前面是提示的话，后面是当对话框出来后，在对话框里的默认值  

			if (NameAndMobile) //如果返回的有内容  
			{
				$.ajax({
					url : 'czpad',
					type : 'post',
					dataType : 'json',
					data : {
						NameAndMobile : NameAndMobile,
						Email : Email
					},
					success : function(result) {
						if (result.type == 'success') {
							alert(result.msg)
							location.href = 'login2';
						} else {
							alert(result.msg)
						}
					}
				});

			} else {
				alert("您未输入任何内容！");
			}

			function Email(NameAndMobile) {
				var Email = prompt("请输入您要重置密码要发送的邮箱", "");
				$.ajax({
					url : 'czpad',
					type : 'post',
					dataType : 'json',
					data : {
						NameAndMobile : NameAndMobile,
						Email : Email
					},
					success : function(result) {
						if (result.type == 'success') {
							alert(result.msg)
							location.href = 'login2';
						} else {
							alert(result.msg)
						}
					}
				});

			}
		})
	</script>
</body>
</html>