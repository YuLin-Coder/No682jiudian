<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>琉璃汀酒店管理系统预定房间</title>
    <meta name="Author" content="琉璃汀酒店">
    <meta name="Keywords" content="琉璃汀酒店管理系统">
    <meta name="Description" content="琉璃汀酒店管理系统">
    <link rel="stylesheet" href="../../resources/home/css/index.css">
    <link rel="stylesheet" href="../../resources/home/css/order.css">
    <link rel="stylesheet" href="../../resources/home/css/jquery-ui.min.css">
</head>
<body>
<!--- 页头--->
<div id="c_header"></div>
<!----主体-->
<div id="section">
    <!--客房信息-->
    <div class="hotel_inf_w">

        <div class="hotel_roominfobox">
            <%--            ../${roomType.photo}--%>
            <a href="#"><img src="${pageContext.request.contextPath}/${roomType.photo}" alt=" "/></a>
            <div class="info">
                <h2>${roomType.name }</h2>
                <!--豪华双人房&#45;&#45;&#45;&#45;预定-->
            </div>
            <ul class="hotel_detail">
                <li><span>预订数:</span>${roomType.bookNum }</li>
                <li><span>房价:</span>${roomType.price }</li>
                <li><span>床位数:</span>${roomType.bedNum }</li>
                <li><span>可住:</span>${roomType.liveNum }人</li>
                <li><span>其他:</span>${roomType.remark }</li>
            </ul>
        </div>
        <div class="jump">

            <a href="../index2">更多房型</a>
        </div>
    </div>
    <!--预定信息-->
    <div class="book_info">
        <form id="order_info">
            <ul>
                <input type="hidden" name="rid" value=""/>
                <li>
                    <h3>预订信息</h3>

                    <div class="info_group">
                        <label>入住时间</label><input type="text" name="arriveDate" id="arriveDate" class="datepicker"/>
                        <label>离店时间</label><input type="text" name="leaveDate" id="leaveDate" class="datepicker"/>
                        <br>
                        <span id="times2" style="color:red"></span>
                    </div>

                    <div class="info_group">
                        <label>房费总计</label><span class="total">￥${roomType.price }</span>
                        <input type="hidden" value="0"/>
                    </div>
                </li>
                <li>
                    <h3>预留信息</h3>

                    <div class="info_group">
                        <label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label><input type="text" name="name"
                                                                                          id="name"
                                                                                          value="${account.name}"/><span
                            class="msg"></span>
                    </div>
                    <div class="info_group">
                        <label>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</label><input type="text" maxlength="11"
                                                                                          name="mobile" id="mobile"
                                                                                          value="${account.mobile}"/><span
                            class="msg"></span>
                    </div>
                    <div class="info_group">
                        <label>身份证号</label><input type="text" name="idCard" id="idCard" value="${account.idCard}"/>
                        <%--                        <span class="msg"></span>--%>
                        <span id="cards" style="color:red"></span>
                    </div>

                    <div class="info_group">
                        <label for="massage">留&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;言</label>
                        <textarea id="remark" name="remark" style="width:200px;"></textarea>
                    </div>

                </li>
                <li>
                    <div class="msg">
                        预订须知:请携带好本人的身份证，办理入住手续，本店办理入住需要在前台缴费押金 ￥500

                    </div>
                    <div id="btn_booking">确认预订</div>

                </li>
            </ul>
        </form>
    </div>

    <div class="malog">
        <div class="message">
            <p class="msgs"></p>
            <p>您可以在 <a href="index#order">我的订单</a>查看详情</p>
            <p>系统会在<span class="num">5</span>秒后跳转会 <a href="../index">菜单列表</a></p>
        </div>
    </div>

</div>
<!----页底--->
<div id="c_footer"></div>
<script src="../../resources/home/js/jquery-1.11.3.js"></script>
<script src="../../resources/home/js/jquery-ui.min.js"></script>
</body>
<script>
    $(function () {
            $("#idCard").blur(function () {

                checker()
            })
            //校验身份证
            function checker() {

                var vals = $("#idCard").val()
                //正则验证
                var chec = new RegExp("^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$")
                var res = chec.test(vals)//返回true或false

                if (res == false) {
                    $("#cards").show().html("身份证号有误请重新输入！！")
                } else {
                    $("#cards").show().html("")
                }
            }

            // --------------------------快乐的分割线-------------
            // 入住时间失焦点
            //把时间格式化
            function GMTToStr(time) {
                let date = new Date(time)
                let Str = date.getFullYear() + '-' +
                    (date.getMonth() + 1) + '-' +
                    date.getDate() + ' ' +
                    date.getHours() + ':' +
                    date.getMinutes() + ':' +
                    date.getSeconds()
                return Str
            }

            // $("#arriveDate").blur(function () {
            //
            //     checker1()
            // })
            //
            // function checker1() {
            //     var vals = $("#arriveDate").val()
            //     var dateNow = new Date()
            //     var date1 = GMTToStr(vals)
            //     if (Date.parse(date1) < Date.parse(dateNow.toDateString())) {
            //         $("#times").show().html("入住时间应晚于当前时间！！")
            //     } else {
            //         $("#times").show().html("")
            //     }
            // }


            $("#leaveDate").blur(function () {

                checker2()
            })

            function checker2() {
                var valsafter = $("#leaveDate").val()
                var valsbefore = $("#arriveDate").val()
                var dateNow = new Date()
                var date1 = GMTToStr(valsafter)
                var date2 = GMTToStr(valsbefore)
                if (Date.parse(date2) < Date.parse(dateNow.toDateString())) {
                    $("#times2").show().html("入住时间应晚于当前时间！！")
                }else {
                    if (Date.parse(date2) > Date.parse(date1)) {
                        $("#times2").show().html("入住时间应先于离店时间！！")
                    } else {
                        if (Date.parse(date1) < Date.parse(dateNow.toDateString())) {
                            $("#times2").show().html("离店时间应晚于当前时间！！")
                        } else {
                            $("#times2").show().html("")
                        }
                    }
                }
            }

            // --------------------------快乐的分割线-------------
            //点击确认预定，调用点击事件，判定是否为空
            $(".datepicker").datepicker({"dateFormat": "yy-mm-dd"});
            $("#btn_booking").click(function () {
                var arriveDate = $("#arriveDate").val();
                var leaveDate = $("#leaveDate").val();
                if (arriveDate == '' || leaveDate == '') {
                    alert('请选择时间!');
                    return;
                }
                var name = $("#name").val();
                if (name == '') {
                    $("#name").next("span.msg").text('请填写入住人!');
                    return;
                }
                $("#name").next("span.msg").text('');
                var mobile = $("#mobile").val();
                if (mobile == '') {
                    $("#mobile").next("span.msg").text('请填写手机号!');
                    return;
                }
                $("#mobile").next("span.msg").text('');
                var idCard = $("#idCard").val();
                if (idCard == '') {
                    $("#idCard").next("span.msg").text('请填写身份证号!');
                    return;
                }
                $("#idCard").next("span.msg").text('');
                var remark = $("#remark").val();
                //调用book_order方法
                $.ajax({
                    url: 'book_order',
                    type: 'post',
                    dataType: 'json',
                    data: {
                        roomTypeId: '${roomType.id }',
                        name: name,
                        mobile: mobile,
                        idCard: idCard,
                        remark: remark,
                        arriveDate: arriveDate,
                        leaveDate: leaveDate
                    },
                    success: function (data) {
                        if (data.type == 'success') {
                            $(".malog").show();
                            setTimeout(function () {
                                window.location.href = 'index';
                            }, 1000)
                        } else {
                            alert(data.msg);
                        }
                    }
                });
            })

        }
    );

</script>
</html>