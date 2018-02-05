/*
 *  Document   : readyLogin.js
 *  Author     : pixelcave
 *  Description: Custom javascript code used in Login page
 */

var ReadyLogin = function () {

    return {
        init: function () {
            $('#form-login').validate({
                onfocusout: function (element) {
                    $(element).valid();
                },
                errorClass: 'help-block animation-slideUp', // You can change the animation class for a different entrance animation - check animations page
                errorElement: 'div',
                errorPlacement: function (error, e) {
                    e.parents('.form-group > div').append(error);
                },
                highlight: function (e) {
                    $(e).closest('.form-group').removeClass('has-success has-error').addClass('has-error');
                    $(e).closest('.help-block').remove();
                },
                success: function (e) {
                    e.closest('.form-group').removeClass('has-success has-error');
                    e.closest('.help-block').remove();
                },
                rules: {
                    'username': {
                        required: true
                    },
                    'host': {
                        required: true
                    },
                    'port': {
                        required: true
                    },
                    'databaseName': {
                        required: true
                    }
                },
                messages: {
                    'username': '请输入数据库帐号..',
                    'host': {
                        required: '请输入数据库地址..'
                    },
                    'port': {
                        required: '请输入数据库端口..'
                    },
                    'databaseName': {
                        required: '请选择数据库....'
                    }
                },
                submitHandler: function () {
                    if($('#databaseName').val() == ''){
                        layer.msg("请选择数据库....");
                        return
                    }
                    var data = $('form').serialize();
                    var url = "/login/submit.json";
                    var l = Ladda.create(document.getElementById('btn-submit'));
                    YzyHttp.easyPostJson(url, data, function (data, textStatus) {
                        if (data.code == '0') {
                            console.log("请求成功:" + JSON.stringify(data));
                            layer.msg(data.msg);
                            window.location = data.url;
                        } else {
                            layer.msg(data.msg);
                        }
                    }, function () {
                        l.start();
                    }, function () {
                        l.stop();
                    });
                },
                invalidHandler: function (form, validator) {
                    return false;
                }
            });
        }
    };
}();


/**
 * 点击搜索功能
 */
$('#btn-search').click(function () {
    var data = $('form').serialize();
    var url = "/login/searchDatabaseNames.json";
    var l = Ladda.create(this);
    YzyHttp.easyPostJson(url, data, function (data, textStatus) {
        if (data.code == 0) {
            var dataList = data.data;
            console.log("请求成功:" + JSON.stringify(data.data));
            $('#databaseName').empty().append('<option></option>');
            for (var i = 0; i < dataList.length; i++) {
                $('#databaseName').append('<option value="' + dataList[i] + '">' + dataList[i] + '</option>')
            }
            layer.msg("获取成功");
            $("#databaseName").trigger("chosen:updated");
        } else {
            layer.msg(data.msg);
        }
    }, function () {
        l.start();
    }, function () {
        l.stop();
    });
});