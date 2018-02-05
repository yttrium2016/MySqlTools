<!DOCTYPE html>
<!--[if IE 9]>         <html class="no-js lt-ie10" lang="zh"> <![endif]-->
<!--[if gt IE 9]><!-->
<html class="no-js" lang="zh"> <!--<![endif]-->
<head>
    <meta charset="utf-8">

    <title>SQL语句查询页面</title>

    <meta name="author" content="杨振宇">

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/plugins.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/themes.css">
    <link rel="stylesheet" href="css/codemirror/codemirror.css">

    <style type="text/css">
        .table > tbody > tr > td {
            text-align: center;
        }

        /* dataTables表头居中 */
        .table > thead:first-child > tr > th {
            text-align: center;
        }

        .CodeMirror {
            border: 1px solid rgba(218, 224, 232, .75);
        }

        .sql-btn {
            float: right;
        !important;
            margin-top: 10px;
        !important;
            margin-right: 14px;
        !important;
        }
    </style>

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
                        <div class="block">
                            <div class="block-title">
                                <h2>输入执行的SQL的查询语句</h2>
                            </div>
                            <textarea id="code" name="code" title="sql语句"></textarea>
                            <div class="row" style="margin-bottom: 15px;">
                                <button id="btn-search" class="btn btn-primary sql-btn">查询
                                </button>
                                <button id="btn-clear" type="button" class="btn btn-danger sql-btn">清空
                                </button>
                                <button id="btn-save" type="button" class="btn btn-info sql-btn">保存
                                </button>
                                <button id="btn-output" type="button" class="btn btn-success sql-btn">导出
                                </button>
                            </div>
                            <div id="div-table" class="table-responsive"
                                 style="border: 1px solid rgba(218, 224, 232, .75);margin-bottom: 15px;">
                                <table id="table"
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

<script src="js/vendor/jquery-2.2.4.min.js"></script>
<script src="js/vendor/modernizr-3.3.1.min.js"></script>
<script src="js/vendor/bootstrap.min.js"></script>
<script src="js/plugins.js"></script>
<script src="js/app.js"></script>
<script src="js/layer/layer.js"></script>
<script src="js/sweetalert.min.js"></script>
<script src="js/codemirror/codemirror.js"></script>
<script src="js/codemirror/sql.js"></script>
<script src="js/common.js"></script>

<script>

    var table = null;
    var editor;
    var dataSet = [];
    var titleSet = [];
    var recordId = "${record.id ! ''}";

    $(function () {
        $('#div-table').hide();
        App.datatables();
        //设置代码输入块
        editor = CodeMirror.fromTextArea(document.getElementById("code"), {
            mode: "text/x-mariadb",
            styleActiveLine: true,
            lineNumbers: true,
            lineWrapping: true
        });
        editor.setSize('auto', '200px');
        editor.getDoc().setValue("${record.sql ! ''}");

        //搜索功能的按键
        $('#btn-search').mouseenter(function () {
            layer.tips('如果没填写LIMIT会在默认查询前500条', '#btn-search', {
                tips: [1, '#0FA6D8'] //还可配置颜色
            });
        }).mouseleave(function () {
            layer.closeAll('tips');
        }).click(function () {
            var sql = editor.getValue().replace(/[\r\n]/g, " ");
            if (!sql) {
                layer.msg("查询语句不能为空");
                return;
            }
            YzyHttp.easyPostJson("/api/executeSQL.json", {sql: sql}, function (data) {
                if (data.code == 0) {
                    var dataList = data.data;
                    titleSet = [];
                    dataSet = [];
                    if (!!dataList) {
                        dataHandle(dataList);
                        initTable();
                    }
                    layer.msg(data.msg);
                } else {
                    layer.msg(data.msg);
                }
            }, function () {
                layer.load(2);
            }, function () {
                layer.closeAll("loading");
            })
        });

        $('#btn-clear').click(function () {
            $('#code').empty();
            editor.getDoc().setValue("");
            editor.refresh();
            $('#div-table').hide();
        });

        $('#btn-save').click(function () {
            var sql = editor.getValue().replace(/[\r\n]/g, " ");
            if (!sql) {
                layer.msg("查询语句不能为空");
                return;
            }
            layer.prompt({title: '输入保存的名字', formType: 0}, function (name, index) {

                YzyHttp.easyPostJson("/sql/save.json", {name: name, sql: sql, recordId: recordId}, function (data) {
                    if (data.code == 0) {
                        layer.msg(data.msg);
                    } else {
                        layer.msg(data.msg);
                    }
                }, function () {
                    layer.load(2);
                }, function () {
                    layer.close(index);
                    layer.closeAll("loading");
                })
            });
        });

        $('#btn-output').click(function () {
            var form = document.createElement("form");
            form.method = "post";
            form.style.display = "none";
            if (!!recordId) {
                form.action = "/sql/output-" + recordId + ".json";
            } else {
                var sql = editor.getValue().replace(/[\r\n]/g, " ");
                form.action = "/sql/output-sql.json";
                var opt = document.createElement("input");
                opt.name = 'sql';
                opt.value = sql;
                form.appendChild(opt);
            }
            document.body.appendChild(form);
            form.submit();
            document.body.removeChild(form);
        });
        init();

    });

    function init() {
        if (!!editor.getValue().replace(/[\r\n]/g, " ")) {
            $('#btn-search').click();
        }
    }

    function initTable() {
        $('#div-table').show();
        if (table != null) {
            table.destroy();
            $('#table').html("");
        }
        table = $('#table').DataTable({
            "processing": false,
            bFilter: false,    //去掉搜索框方法三：这种方法可以
            "sPaginationType": "bootstrap",
            scrollCollapse: false,
            "ordering": false, //关闭排序
            "lengthMenu": [[10, 20, 50, 100], ["10条", "20条", "50条", "100条"]],
            data: dataSet,
            columns: titleSet,
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
    }

    function dataHandle(data) {
        for (var i = 0; i < data.length; i++) {
            var map = data[i];
            var t = [];
            for (var key in map) {
                if (i === 0) {
                    var obj = {};
                    obj.title = key;
                    titleSet.push(obj);
                }
                t.push(map[key] + "");
            }
            dataSet.push(t);
        }
    }

</script>

</body>
</html>