$(function(){ 
	//短信验证码倒计时
	var countdownHandler = function(){
		var $button = $(".sendVerifyCode");
		var number = 60;
		var countdown = function(){
			if (number == 0) {
				$button.attr("disabled",false);
				$button.html("发送验证码");
	            number = 60;
	            return;
	        } else {
	        	$button.attr("disabled",true);
	        	$button.html(number + "秒 重新发送");
	        	number--;
	        }
			setTimeout(countdown,1000);
		}
		setTimeout(countdown,1000);
	}
	//发送短信验证码
	$(".sendVerifyCode").on("click", function(){
		var $number = $("input[name=number]");
		var data = {};
		data.number = $.trim($("input[name=number]").val());
		data.templateId = $.trim($("input[name=templateId]").val());
		if(data.number == ''){
			alert("请输入手机号码");
			return ;
		}
		if(data.templateId == ''){
			alert("请输入模板ID");
			return ;
		}
		$.ajax({
	        url: getBasePath()+"/message/exSend",
	        async : true,
	        type: "post",
	        dataType: "text",
	        data: data,
	        success: function (data) {
	        	if(data == 'success'){
	        		countdownHandler();
	        		return ;
	        	}
	        	alert(data);
	        }
    	});
	})
	//提交
	$(".sub-btn").on("click", function(){
		var data = {};
		data.number = $.trim($("input[name=number]").val());
		data.verifyCode = $.trim($("input[name=verifyCode]").val());
		if(data.verifyCode == ''){
			alert("请输入验证码");
			return ;
		}
		$.ajax({
	        url: getBasePath()+"/register/exRegister",
	        async : true,
	        type: "post",
	        dataType: "text",
	        data: data,
	        success: function (data) {
	        	if(data == 'success'){
	        		alert("注册成功");
	        		return ;
	        	}
	        	alert(data);
	        }
    	});
	})
});
