<!DOCTYPE html>
<!--[if IE 9]>         <html class="no-js lt-ie10" lang="en"> <![endif]-->
<!--[if gt IE 9]><!-->
<html class="no-js" lang="en"> <!--<![endif]-->
<head>
    <meta charset="utf-8">

    <title>登录页面</title>

    <meta name="author" content="杨振宇">
    <!-- Icons -->
    <!-- The following icons can be replaced with your own, they are used by desktop and mobile browsers -->
    <link rel="shortcut icon" href="img/favicon.png">
    <link rel="apple-touch-icon" href="img/icon57.png" sizes="57x57">
    <link rel="apple-touch-icon" href="img/icon72.png" sizes="72x72">
    <link rel="apple-touch-icon" href="img/icon76.png" sizes="76x76">
    <link rel="apple-touch-icon" href="img/icon114.png" sizes="114x114">
    <link rel="apple-touch-icon" href="img/icon120.png" sizes="120x120">
    <link rel="apple-touch-icon" href="img/icon144.png" sizes="144x144">
    <link rel="apple-touch-icon" href="img/icon152.png" sizes="152x152">
    <link rel="apple-touch-icon" href="img/icon180.png" sizes="180x180">
    <!-- END Icons -->

    <!-- Stylesheets -->
    <!-- Bootstrap is included in its original form, unaltered -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!-- Related styles of various icon packs and plugins -->
    <link rel="stylesheet" href="css/plugins.css">

    <!-- The main stylesheet of this template. All Bootstrap overwrites are defined in here -->
    <link rel="stylesheet" href="css/main.css">

<#-- 按钮增强 -->
    <link rel="stylesheet" href="css/ladda/ladda-themeless.min.css">

    <style>
        .chosen-container .chosen-results {
            color: #444;
            position: relative;
            overflow-x: hidden;
            overflow-y: auto;
            margin: 0 4px 4px 0;
            padding: 0 0 0 4px;
            max-height: 130px;
            -webkit-overflow-scrolling: touch;
        }
    </style>

    <!-- Include a specific file here from css/themes/ folder to alter the default theme of the template -->


    <!-- The themes stylesheet of this template (for using specific theme color in individual elements - must included last) -->
    <link rel="stylesheet" href="css/themes.css">
    <!-- END Stylesheets -->

    <!-- Modernizr (browser feature detection library) -->
    <script src="js/vendor/modernizr-3.3.1.min.js"></script>
</head>
<body>

<!-- Login Container -->
<div id="login-container">
    <!-- Login Header -->
    <h1 class="h2 text-light text-center push-top-bottom animation-slideDown">
        <i class="fa fa-cube"></i> <strong>数据库管理系统</strong>
    </h1>
    <!-- END Login Header -->

    <!-- Login Block -->
    <div class="block animation-fadeInQuickInv">
        <!-- Login Title -->
        <div class="block-title">
            <div class="block-options pull-right">
                <a href="#!" class="btn btn-effect-ripple btn-primary" data-toggle="tooltip" data-placement="left"
                   title="忘记密码提示?"><i class="fa fa-exclamation-circle"></i></a>
                <a href="#+" class="btn btn-effect-ripple btn-primary" data-toggle="tooltip" data-placement="left"
                   title="新建用户提示!"><i class="fa fa-plus"></i></a>
            </div>
            <h2>请登录</h2>
        </div>
        <!-- END Login Title -->

        <!-- Login Form -->
        <form id="form-login" action="/login/submit.action" method="post" class="form-horizontal">

        <#-- 数据库服务器地址 -->
            <div class="form-group">
                <div class="col-xs-12">
                    <input type="text" id="host" name="host" class="form-control" value=""
                           placeholder="数据库地址..">
                </div>
            </div>
        <#-- 数据库服务器端口号 -->
            <div class="form-group">
                <div class="col-xs-12">
                    <input type="text" id="port" name="port" class="form-control" value="" placeholder="数据库端口..">
                </div>
            </div>
        <#-- 数据库服务器帐号 -->
            <div class="form-group">
                <div class="col-xs-12">
                    <input type="text" id="username" name="username" class="form-control" value=""
                           placeholder="数据库帐号..">
                </div>
            </div>
        <#-- 数据库服务器密码 -->
            <div class="form-group">
                <div class="col-xs-12">
                    <input type="password" id="password" name="password" class="form-control" value=""
                           placeholder="数据库密码..">
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-12">
                    <div class="input-group">
                    <#--<select id="databaseName" name="databaseName" class="select-select2" style="width: 100%;"-->
                        <select id="databaseName" name="databaseName" class="select-chosen required"
                                style="width: 100%;"
                                data-placeholder="选择数据库..">
                            <option></option>
                        </select>
                        <span class="input-group-btn">
                            <button id="btn-search" type="button" class="btn btn-effect-ripple btn-primary ladda-button"
                                    data-style="slide-up"
                                    style="overflow: hidden; position: relative;"><span
                                    class="btn-ripple animate"
                                    style="height: 39px; width: 39px; top: 1.5px; left: 4.125px;"></span><i
                                    id="i-search"
                                    class="hi hi-search"></i></button>
                        </span>
                    </div>
                </div>
            </div>
            <div class="form-group form-actions">
                <div class="col-xs-8">
                    <label class="csscheckbox csscheckbox-primary">
                        <input disabled="disabled" type="checkbox" id="login-remember-me" name="login-remember-me">
                        <span></span>
                    </label>
                    记住帐号?
                </div>
                <div class="col-xs-4 text-right">
                    <button id="btn-submit" type="submit" class="btn btn-effect-ripple btn-sm btn-primary ladda-button"
                            data-style="slide-up"><i class="fa fa-check"></i>
                        登录
                    </button>
                </div>
            </div>
        </form>
    </div>
    <footer class="text-muted text-center animation-pullUp">
        <small><span id="year-copy"></span> &copy; <a href="#杨振宇" target="">数据库</a> 1.0</small>
    </footer>
</div>
<script src="js/vendor/jquery-2.2.4.min.js"></script>
<script src="js/vendor/bootstrap.min.js"></script>
<script src="js/plugins.js"></script>
<script src="js/sweetalert.min.js"></script>
<script src="js/ladda/spin.min.js"></script>
<script src="js/ladda/ladda.min.js"></script>
<script src="js/common.js"></script>
<script src="js/layer/layer.js"></script>
<script src="js/app.js"></script>

<script src="js/pages/readyLogin.js"></script>
<script type="text/javascript">
    $(function () {
        ReadyLogin.init();
    });

    /* 鼠标特效 */
    var body_index = 0
    jQuery(document).ready(function ($) {
        $("body").click(function (e) {
            var a = new Array("富强", "民主", "文明", "和谐", "自由", "平等", "公正", "法治", "爱国", "敬业", "诚信", "友善");
            var $i = $("<span/>").text(a[body_index]);
            body_index = (body_index + 1) % a.length;
            var x = e.pageX,
                    y = e.pageY;
            $i.css({
                "z-index": 999999999999999999999999999999999999999999999999999999999999999999999,
                "top": y - 20,
                "left": x,
                "position": "absolute",
                "font-weight": "bold",
                "color": "#ff4444"
            });
            $("body").append($i);
            $i.animate({
                        "top": y - 180,
                        "opacity": 0
                    },
                    1500,
                    function () {
                        $i.remove();
                    });
        });
    });
</script>
</script>
<
/body>
< /html>