<!DOCTYPE html>
<!--[if IE 9]>         <html class="no-js lt-ie10" lang="zh"> <![endif]-->
<!--[if gt IE 9]><!-->
<html class="no-js" lang="zh "> <!--<![endif]-->
<head>
    <meta charset="utf-8">

    <title>主页面</title>

    <meta name="author" content="杨振宇">
    <!-- Icons -->
    <!-- 下面的图标可以替换为你自己的,他们所使用的桌面和移动浏览器 -->
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
    <!-- 引导程序包括在原来的形式,没有改变 -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!-- 相关的各种风格和插件图标包 -->
    <link rel="stylesheet" href="css/plugins.css">

    <!-- 这个模板的主要样式表。定义所有引导覆盖在这里 -->
    <link rel="stylesheet" href="css/main.css">

    <!-- 包括一个特定的文件从css/themes/文件夹改变模板的默认主题 -->

    <!-- 这个模板的主题样式表(使用特定的主题颜色在单个元素——必须包括最后) -->
    <link rel="stylesheet" href="css/themes.css">
    <!-- END Stylesheets -->

    <style>
        .nav.navbar-nav-custom > li > select {
            min-width: 50px;
            padding: 5px 7px;
            line-height: 40px;
            text-align: center;
            position: relative;
        }
    </style>
    <!-- Modernizr(浏览器特性检测库) -->
    <script src="js/vendor/modernizr-3.3.1.min.js"></script>
</head>
<body>
<!-- Page Wrapper -->
<!-- In the PHP version you can set the following options from inc/config file -->
<!--
    Available classes:

    'page-loading'      enables page preloader
-->
<div id="page-wrapper" class="page-loading">
    <!-- Preloader -->
    <!-- Preloader functionality (initialized in js/app.js) - pageLoading() -->
    <!-- Used only if page preloader enabled from inc/config (PHP version) or the class 'page-loading' is added in #page-wrapper element (HTML version) -->
    <div class="preloader">
        <div class="inner">
            <!-- 所有现代浏览器动画转轮 -->
            <div class="preloader-spinner themed-background hidden-lt-ie10"></div>

            <!-- Text for IE9 -->
            <h3 class="text-primary visible-lt-ie10"><strong>Loading..</strong></h3>
        </div>
    </div>
    <!-- END Preloader -->

    <!-- Page Container -->
    <!-- In the PHP version you can set the following options from inc/config file -->
    <!--
        Available #page-container classes:

        'sidebar-light'                                 主要的光栏(您可以添加它连同其他类)

        'sidebar-visible-lg-mini'                       主要栏浓缩——迷你导航(> 991 px)
        'sidebar-visible-lg-full'                       主要栏全——全导航(> 991 px)

        'sidebar-alt-visible-lg'                        选择栏可见默认情况下(> 991 px)(你可以把它连同其他类)

        'header-fixed-top'                              只有添加如果类header.navbar“navbar-fixed-top”了吗
        'header-fixed-bottom'                           只有添加如果类header.navbar“navbar-fixed-bottom”了吗

        'fixed-width'                                   固定宽度的布局(只能使用一个静态的header/main栏布局)

        'enable-cookies'                                记住积极的颜色主题使饼干当从侧边栏链接(你可以把它连同其他类)
    -->
    <div id="page-container" class="header-fixed-top sidebar-visible-lg-full enable-cookies">
        <!-- Alternative Sidebar -->
        <div id="sidebar-alt" tabindex="-1" aria-hidden="true">
            <!-- Toggle Alternative Sidebar Button (在静态布局才可见) -->
            <a href="javascript:void(0)" id="sidebar-alt-close" onclick="App.sidebar('toggle-sidebar-alt');"><i
                    class="fa fa-times"></i></a>

            <!-- 包装器滚动功能 Wrapper for scrolling functionality -->
            <div id="sidebar-scroll-alt">
                <!-- 右侧边栏内容 Sidebar Content -->
                <div class="sidebar-content">
                    <!-- END Profile -->

                    <div class="sidebar-section sidebar-nav-mini-hide">
                        <h2 class="text-light">皮肤</h2>
                        <ul class="sidebar-themes clearfix">
                            <li>
                                <a href="javascript:void(0)" class="themed-background-default" data-toggle="tooltip"
                                   title="Default" data-theme="default" data-theme-navbar="navbar-inverse"
                                   data-theme-sidebar="">
                                    <span class="section-side themed-background-dark-default"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-classy" data-toggle="tooltip"
                                   title="Classy" data-theme="css/themes/classy.css" data-theme-navbar="navbar-inverse"
                                   data-theme-sidebar="">
                                    <span class="section-side themed-background-dark-classy"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-social" data-toggle="tooltip"
                                   title="Social" data-theme="css/themes/social.css" data-theme-navbar="navbar-inverse"
                                   data-theme-sidebar="">
                                    <span class="section-side themed-background-dark-social"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-flat" data-toggle="tooltip"
                                   title="Flat" data-theme="css/themes/flat.css" data-theme-navbar="navbar-inverse"
                                   data-theme-sidebar="">
                                    <span class="section-side themed-background-dark-flat"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-amethyst" data-toggle="tooltip"
                                   title="Amethyst" data-theme="css/themes/amethyst.css"
                                   data-theme-navbar="navbar-inverse" data-theme-sidebar="">
                                    <span class="section-side themed-background-dark-amethyst"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-creme" data-toggle="tooltip"
                                   title="Creme" data-theme="css/themes/creme.css" data-theme-navbar="navbar-inverse"
                                   data-theme-sidebar="">
                                    <span class="section-side themed-background-dark-creme"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-passion" data-toggle="tooltip"
                                   title="Passion" data-theme="css/themes/passion.css"
                                   data-theme-navbar="navbar-inverse" data-theme-sidebar="">
                                    <span class="section-side themed-background-dark-passion"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-default" data-toggle="tooltip"
                                   title="Default + Light Sidebar" data-theme="default"
                                   data-theme-navbar="navbar-inverse" data-theme-sidebar="sidebar-light">
                                    <span class="section-side"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-classy" data-toggle="tooltip"
                                   title="Classy + Light Sidebar" data-theme="css/themes/classy.css"
                                   data-theme-navbar="navbar-inverse" data-theme-sidebar="sidebar-light">
                                    <span class="section-side"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-social" data-toggle="tooltip"
                                   title="Social + Light Sidebar" data-theme="css/themes/social.css"
                                   data-theme-navbar="navbar-inverse" data-theme-sidebar="sidebar-light">
                                    <span class="section-side"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-flat" data-toggle="tooltip"
                                   title="Flat + Light Sidebar" data-theme="css/themes/flat.css"
                                   data-theme-navbar="navbar-inverse" data-theme-sidebar="sidebar-light">
                                    <span class="section-side"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-amethyst" data-toggle="tooltip"
                                   title="Amethyst + Light Sidebar" data-theme="css/themes/amethyst.css"
                                   data-theme-navbar="navbar-inverse" data-theme-sidebar="sidebar-light">
                                    <span class="section-side"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-creme" data-toggle="tooltip"
                                   title="Creme + Light Sidebar" data-theme="css/themes/creme.css"
                                   data-theme-navbar="navbar-inverse" data-theme-sidebar="sidebar-light">
                                    <span class="section-side"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-passion" data-toggle="tooltip"
                                   title="Passion + Light Sidebar" data-theme="css/themes/passion.css"
                                   data-theme-navbar="navbar-inverse" data-theme-sidebar="sidebar-light">
                                    <span class="section-side"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-default" data-toggle="tooltip"
                                   title="Default + Light Header" data-theme="default"
                                   data-theme-navbar="navbar-default" data-theme-sidebar="">
                                    <span class="section-header"></span>
                                    <span class="section-side themed-background-dark-default"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-classy" data-toggle="tooltip"
                                   title="Classy + Light Header" data-theme="css/themes/classy.css"
                                   data-theme-navbar="navbar-default" data-theme-sidebar="">
                                    <span class="section-header"></span>
                                    <span class="section-side themed-background-dark-classy"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-social" data-toggle="tooltip"
                                   title="Social + Light Header" data-theme="css/themes/social.css"
                                   data-theme-navbar="navbar-default" data-theme-sidebar="">
                                    <span class="section-header"></span>
                                    <span class="section-side themed-background-dark-social"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-flat" data-toggle="tooltip"
                                   title="Flat + Light Header" data-theme="css/themes/flat.css"
                                   data-theme-navbar="navbar-default" data-theme-sidebar="">
                                    <span class="section-header"></span>
                                    <span class="section-side themed-background-dark-flat"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-amethyst" data-toggle="tooltip"
                                   title="Amethyst + Light Header" data-theme="css/themes/amethyst.css"
                                   data-theme-navbar="navbar-default" data-theme-sidebar="">
                                    <span class="section-header"></span>
                                    <span class="section-side themed-background-dark-amethyst"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-creme" data-toggle="tooltip"
                                   title="Creme + Light Header" data-theme="css/themes/creme.css"
                                   data-theme-navbar="navbar-default" data-theme-sidebar="">
                                    <span class="section-header"></span>
                                    <span class="section-side themed-background-dark-creme"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                            <li>
                                <a href="javascript:void(0)" class="themed-background-passion" data-toggle="tooltip"
                                   title="Passion + Light Header" data-theme="css/themes/passion.css"
                                   data-theme-navbar="navbar-default" data-theme-sidebar="">
                                    <span class="section-header"></span>
                                    <span class="section-side themed-background-dark-passion"></span>
                                    <span class="section-content"></span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- 右侧边栏内容 END Sidebar Content -->
            </div>
            <!-- END Wrapper for scrolling functionality -->
        </div>
        <!-- END Alternative Sidebar -->

        <!-- Main Sidebar -->
        <div id="sidebar">
            <!-- Sidebar Brand -->
            <!-- 左边侧边栏上面的图片和Name -->
            <div id="sidebar-brand" class="themed-background">
                <a href="/index.shtml" class="sidebar-title">
                    <i class="fa fa-cube"></i> <span class="sidebar-nav-mini-hide">Brick-<strong>Y</strong></span>
                </a>
            </div>
            <!-- END Sidebar Brand -->

            <!-- Wrapper for scrolling functionality -->
            <div id="sidebar-scroll">
                <!-- Sidebar Content -->
                <div class="sidebar-content">
                    <!-- Sidebar Navigation -->
                    <ul class="sidebar-nav">

                        <li>
                            <a href="sql.shtml"><i class="gi gi-inbox sidebar-nav-icon"></i><span
                                    class="sidebar-nav-mini-hide">执行SQL</span></a>
                        </li>
                        <li>
                            <a href="#" class="sidebar-nav-menu">
                                <i class="fa fa-chevron-left sidebar-nav-indicator sidebar-nav-mini-hide"></i>
                                <i class="gi gi-more_items sidebar-nav-icon"></i>
                                <span class="sidebar-nav-mini-hide">数据表</span>
                            </a>
                            <ul>
                            <#if tableNameList?? && (tableNameList?size > 0) >
                                <#list tableNameList!"" as tableName>
                                    <li>
                                        <a href="table-${tableName ! ""}.shtml">${tableName ! ""}</a>
                                    </li>
                                </#list>
                            </#if>

                            </ul>
                        </li>
                        <li>
                            <a href="#" class="sidebar-nav-menu"><i
                                    class="fa fa-chevron-left sidebar-nav-indicator sidebar-nav-mini-hide"></i><i
                                    class="gi gi-airplane sidebar-nav-icon"></i><span class="sidebar-nav-mini-hide">保存的语句</span></a>
                            <ul>
                            <#if SqlRecordList?? && (SqlRecordList?size > 0) >
                                <#list SqlRecordList!"" as SqlRecord>
                                    <li>
                                        <a href="sql-${SqlRecord.id ! ""}.shtml">${SqlRecord.name ! ""}</a>
                                    </li>
                                </#list>
                            </#if>

                            </ul>
                        </li>

                    </ul>
                </div>
            </div>
            <div id="sidebar-extra-info" class="sidebar-content sidebar-nav-mini-hide">
                <div class="text-center">
                <#-- target="_blank" -->
                    <small>Crafted with <i class="fa fa-heart text-danger"></i> by <a href="/">yttrium2016</a>
                    </small>
                    <br>
                <#-- target="_blank" -->
                    <small><span id="year-copy"></span> &copy; <a href="/">Brick-Y</a></small>
                </div>
            </div>
            <!-- END Sidebar Extra Info -->
        </div>
        <!-- END Main Sidebar -->

        <!-- Main Container -->
        <div id="main-container">
            <!-- Header -->
            <!-- In the PHP version you can set the following options from inc/config file -->
            <!--
                Available header.navbar classes:

                'navbar-default'            for the default light header
                'navbar-inverse'            for an alternative dark header

                'navbar-fixed-top'          for a top fixed header (fixed main sidebar with scroll will be auto initialized, functionality can be found in js/app.js - handleSidebar())
                    'header-fixed-top'      has to be added on #page-container only if the class 'navbar-fixed-top' was added

                'navbar-fixed-bottom'       for a bottom fixed header (fixed main sidebar with scroll will be auto initialized, functionality can be found in js/app.js - handleSidebar()))
                    'header-fixed-bottom'   has to be added on #page-container only if the class 'navbar-fixed-bottom' was added
            -->
            <header class="navbar navbar-inverse navbar-fixed-top">
                <!-- Left Header Navigation -->
                <ul class="nav navbar-nav-custom">
                    <!-- Main Sidebar Toggle Button -->
                    <li>
                        <a href="javascript:void(0)" onclick="App.sidebar('toggle-sidebar');this.blur();">
                            <i class="fa fa-ellipsis-v fa-fw animation-fadeInRight" id="sidebar-toggle-mini"></i>
                            <i class="fa fa-bars fa-fw animation-fadeInRight" id="sidebar-toggle-full"></i>
                        </a>
                    </li>
                    <!-- END Main Sidebar Toggle Button -->

                    <li>
                        <a href="javascript:void(0)" data-toggle="dropdown"
                           class="btn-link dropdown-toggle">当前数据库:<strong>${defaultDataName !''}</strong>
                            <span class="caret"></span></a>
                        <ul id="dataNames-ul" class="dropdown-menu text-left"
                            style="max-height: 400px; overflow-y: auto">
                        <#if dataNameList?? && (dataNameList?size > 0) >
                            <#list dataNameList!"" as dataName>
                                <#if dataName?? == defaultDataName >
                                    <li class="dropdown-header">
                                        <i class="fa fa-check pull-right"></i>
                                        <strong>${dataName!''}</strong>
                                    </li>
                                </#if>

                                <#if dataName?? != defaultDataName >
                                    <li>
                                        <a href="javascript:void(0)" data="${dataName!''}"
                                           class="data-name-ul">${dataName!''}</a>
                                    </li>
                                </#if>
                            </#list>
                        </#if>
                        </ul>
                    </li>


                    <!-- END Header Link -->
                </ul>
                <!-- END Left Header Navigation -->

                <!-- Right Header Navigation -->
                <ul class="nav navbar-nav-custom pull-right">
                    <!-- END Search Form -->

                    <!-- Alternative Sidebar Toggle Button -->
                    <li>
                        <a href="javascript:void(0)" onclick="App.sidebar('toggle-sidebar-alt');this.blur();">
                            <i class="gi gi-settings"></i>
                        </a>
                    </li>
                    <!-- END Alternative Sidebar Toggle Button -->

                    <!-- User Dropdown -->
                    <li class="dropdown">
                        <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="img/logo.jpg" alt="avatar">
                        </a>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li>
                                <a href="/loginOut.shtml">
                                    <i class="fa fa-power-off fa-fw pull-right"></i>
                                    退出
                                </a>
                            </li>
                        </ul>
                    </li>
                    <!-- END User Dropdown -->
                </ul>
                <!-- END Right Header Navigation -->
            </header>
            <!-- END Header -->

            <!-- Page content style="padding: 50px 0px 0px 0px" 去掉边距 -->
            <div id="page-content" style="padding: 50px 0px 0px 0px">
                <iframe src="welcome.shtml" scrolling="auto" frameBorder=0 width="100%" id="main-content">

                </iframe>
            </div>
            <!-- END Page Content -->
        </div>
        <!-- END Main Container -->
    </div>
    <!-- END Page Container -->
</div>
<!-- END Page Wrapper -->

<!-- jQuery, Bootstrap, jQuery plugins and Custom JS code -->
<script src="js/vendor/jquery-2.2.4.min.js"></script>
<script src="js/vendor/bootstrap.min.js"></script>
<script src="js/plugins.js"></script>
<script src="js/iframe.js"></script>
<script src="js/app.js"></script>
<script src="js/common.js"></script>
<script src="js/layer/layer.js"></script>
<script>

    $('.data-name-ul').click(function () {
        var dataName = $(this).attr("data");
        YzyHttp.easyPostJson("/login/updateDataName.json", {dataName: dataName}, function (data) {
            if (data.code == 0) {
                location.reload();
            }
        });
//        var form = document.createElement('form');
//        form.style = "display:none;";
//        form.method = 'get';
//        form.action = '/index.shtml';
//        var input = document.createElement('input');
//        input.type = 'hidden';
//        input.name = 'dataName';
//        input.value = dataName;
//        form.appendChild(input);
//        document.body.appendChild(form);
//        form.submit();
//        document.body.removeChild(form);
    });
</script>
</body>
</html>