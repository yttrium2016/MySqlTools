<!DOCTYPE html>
<!--[if IE 9]>         <html class="no-js lt-ie10" lang="zh"> <![endif]-->
<!--[if gt IE 9]><!-->
<html class="no-js" lang="zh"> <!--<![endif]-->
<head>
    <meta charset="utf-8">

    <title>表页面</title>

    <meta name="author" content="杨振宇">

    <link rel="shortcut icon" href="img/favicon.png">
    <link rel="apple-touch-icon" href="img/icon57.png" sizes="57x57">
    <link rel="apple-touch-icon" href="img/icon72.png" sizes="72x72">
    <link rel="apple-touch-icon" href="img/icon76.png" sizes="76x76">
    <link rel="apple-touch-icon" href="img/icon114.png" sizes="114x114">
    <link rel="apple-touch-icon" href="img/icon120.png" sizes="120x120">
    <link rel="apple-touch-icon" href="img/icon144.png" sizes="144x144">
    <link rel="apple-touch-icon" href="img/icon152.png" sizes="152x152">
    <link rel="apple-touch-icon" href="img/icon180.png" sizes="180x180">
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <link rel="stylesheet" href="css/plugins.css">

    <link rel="stylesheet" href="css/main.css">

    <style>
        .table > tbody > tr > td {
            text-align: center;
        }

        /* dataTables表头居中 */
        .table > thead:first-child > tr > th {
            text-align: center;
        }

    </style>

    <link rel="stylesheet" href="css/themes.css">


</head>
<body>
<div id="page-wrapper" class="page-loading">
    <div class="preloader">
        <div class="inner">
            <div class="preloader-spinner themed-background hidden-lt-ie10"></div>

            <h3 class="text-primary visible-lt-ie10"><strong>Loading..</strong></h3>
        </div>
    </div>
    <div class="page-container">
        <div id="main-container">
            <div id="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="block full">
                            <div class="block-section">
                                <h4>表名: ${tableName ! ""}
                                    <button id="btn-add" style="float: right;" class="btn btn-primary">添加 <i
                                            class="fa fa-plus"></i>
                                    </button>
                                    <button id="btn-output" type="button" style="float: right;margin-right: 10px"
                                            class="btn btn-info">
                                        导出
                                        <i class="fa fa-arrow-circle-down"></i>
                                    </button>
                                </h4>
                            </div>

                            <div class="table-responsive" style="border: 1px solid rgba(218, 224, 232, .75);">
                                <table id="table-datatable"
                                       class="table table-center table-borderless table-striped table-hover table-condensed ">
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="view" style="display: none;">
    <div id="add-view" class="row" style="width: 750px;">
        <div style="margin-top: 30px" class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="form-add" class="form-horizontal">
                    <#if columnList?? && (columnList?size > 0) >
                        <#list columnList!"" as column>
                            <div class="form-group col-xs-6">
                                <label class="col-xs-3 control-label">${column.columnName !''}:</label>
                                <div class="col-xs-7">
                                    <input type="text" id="input_${column.columnName !''}"
                                           placeholder="${column.columnTypeName ! ''}" name="${column.columnName ! ''}"
                                           class="form-control">
                                    <span class="help-block m-b-none"></span>
                                </div>
                                <#if column?? && (column.autoIncrement) >
                                    <label class="col-xs-2 control-label">自增</label>
                                </#if>
                            <#--<div class="col-xs-2">-->
                            <#--<label class="switch switch-primary" style="margin: 2px"><input-->
                            <#--id="checkbox_${column.columnName !''}" type="checkbox"><span-->
                            <#--style="margin: 2px"></span></label>-->
                            <#--</div>-->
                            </div>
                        <#--{"data": "${column.columnName !''}", "title": "${column.columnName !''}"},-->
                        </#list>
                    </#if>
                        <div class="col-xs-12 row">
                            <div class="col-xs-5"></div>
                            <div class="col-xs-2">
                                <button type="reset" class="btn btn-effect-ripple btn-danger"
                                        style="overflow: hidden; position: relative;float: right;">清空
                                </button>
                                <button type="button" onclick="addItem()" class="btn btn-effect-ripple btn-primary"
                                        style="overflow: hidden; position: relative;">提交
                                </button>
                            </div>
                            <div class="col-xs-5"></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="js/vendor/jquery-2.2.4.min.js"></script>
<script src="js/vendor/modernizr-3.3.1.min.js"></script>
<script src="js/vendor/bootstrap.min.js"></script>
<script src="js/plugins.js"></script>
<script src="js/handlebars-v4.0.10.js"></script>
<script src="js/app.js"></script>
<script src="js/sweetalert.min.js"></script>
<script src="js/common.js"></script>
<script src="js/pages/uiTables.js"></script>
<script src="js/layer/layer.js"></script>

<#--按钮的模版 其实可以直接写 但是我就是这么写 任性-->
<script id="btnTpl" type="text/x-handlebars-template">
    {{#each buttons}}
    <button type="button" oid="{{this.oid}}" class="btn btn-xs {{this.type}}" disabled="disabled" onclick="{{this.fn}}">
        {{this.name}}
    </button>
    {{/each}}
</script>


<script>

    var tableName = '${tableName ! ""}';
    var addUrl = '/add-' + tableName + '.json';
    var outputUrl = '/output-' + tableName + '.json';

    $(function () {
        UiTables.init();

        $('#btn-add').click(function () {
            var index = layer.open({
                type: 1,
                title: "添加信息",
                content: $("#add-view").html(),
                area: ['750px', '400px'],
                maxmin: true
            });
            layer.full(index);
        });

        $('#btn-output').click(function () {
            var form = document.createElement("form");
            form.action = outputUrl;
            form.method = "post";
            form.style.display = "none";
            document.body.appendChild(form);
            form.submit();
            document.body.removeChild(form);
        });

        var btnTpl = $("#btnTpl").html();
        //预编译模板
        var template = Handlebars.compile(btnTpl);

        var table = $('#table-datatable').dataTable({
            "processing": false,
            "serverSide": true,
            "sPaginationType": "bootstrap",
            scrollCollapse: false,
            rowId: 'id',
            "lengthMenu": [[10, 20, 50, -1], ["10条", "20条", "50条", "所有数据"]],
            "ajax": {
                url: "/api/getData.do?tableName=" + tableName,
                type: "POST",
                beforeSend: function (XMLHttpRequest) {
                    //请求前的处理
                    layer.load(2);
                },
                complete: function (XMLHttpRequest, textStatus) {
                    //请求完成的处理
                    layer.closeAll("loading");
                }
            },
            "ordering": false, //关闭排序
            "columns": [
            <#if columnList?? && (columnList?size > 0) >
                <#list columnList!"" as column>
                    {"data": "${column.columnName !''}", "title": "${column.columnName !''}"},
                </#list>
            </#if>
                {"data": null, "title": "操作"}
            ],
            "columnDefs": [{
                "targets": -1,
                "searchable": false,
                "orderable": false,
                "render": function (data, type, row, meta) {
                    var context =
                            {
                                buttons: [
                                    {
                                        "name": "暂无",
                                        "fn": "edit(" + data.id + ")",
                                        "type": "btn-danger",
                                        "oid": data.id
                                    }
                                ]
                            };
                    return template(context);
                }
            }],
            "language": {
                "lengthMenu": "_MENU_ &nbsp;条记录每页",
                "zeroRecords": "没有找到记录",
                "info": "第_PAGE_ 页/_PAGES_ 页,共 _TOTAL_ 条记录",
                "infoEmpty": "无记录",
                "search": "查询:&nbsp;",
                "infoFiltered": "(从 _MAX_ 条记录过滤)",
                "loadingRecords": "请等待，数据正在加载中......",
                "processing": "请等待，加载数据中......",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "previous": "上一页",
                    "next": "下一页"
                }
            }
        });

        $('#table-datatable tbody').on('click', 'tr', function () {
            $(this).toggleClass('info');
            $(this).toggleClass('tr-selected');
        });

        //多选后的按键获取ids
        $('#button').click(function () {
            var ids = "";
            $('.tr-selected').each(function (index, element) {
                if (index == 0) {
                    ids = $(this).attr("id");
                } else {
                    ids += "," + $(this).attr("id");
                }
            })
            layer.msg(ids);
        });
    });


    function addItem() {
        var data = $('form:eq(1)').serialize();
        YzyHttp.easyPostJson(addUrl, data, function (data) {
            if (data.code == 0) {
                parent.layer.msg(data.msg);
                location.reload();
            } else {
                layer.msg(data.msg);
            }
        }, function () {
            layer.load(2);
        }, function () {
            layer.closeAll("loading");
        });
    }

</script>

</body>
</html>