var YzyHttp = {

    /**
     * 杨振宇的自定义ajax封装类
     *
     * @param url 请求地址
     * @param data 请求参数
     * @param isPost 是否是post请求
     * @param successCallBack 成功回调
     * @param beforeSendCallBack 请求前回调
     * @param completeCallBack 请求后回调
     * @param errorCallBack 失败回调
     * @param dataFilterCallBack 数据处理拦截回调
     * @param dataType 返回值参数类型
     * @constructor
     */
    ajax: function (url, data, isPost, successCallBack, beforeSendCallBack, completeCallBack, errorCallBack, dataFilterCallBack, dataType) {
        var type = Boolean(isPost) ? "POST" : "GET";
        var _dataType = !dataType ? "JSON" : dataType;
        $.ajax({
            url: url,    //请求的url地址
            dataType: _dataType,   //返回格式为json
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
            data: data,    //参数值
            cache: false,
            type: type,   //请求方式
            beforeSend: function (XMLHttpRequest) {
                //请求前的处理
                if (!!beforeSendCallBack)
                    beforeSendCallBack(XMLHttpRequest);
                console.log("beforeSend");
            },
            dataFilter: function (data, dataType) {
                //请求的数据进行改装
                if (!!dataFilterCallBack)
                    return dataFilterCallBack(data, dataType)
                console.log("dataFilter");
                return data;
            },
            success: function (data, textStatus) {
                //请求成功时处理
                if (!!successCallBack)
                    successCallBack(data, textStatus);
                console.log("success");
            },
            complete: function (XMLHttpRequest, textStatus) {
                //请求完成的处理
                if (!!completeCallBack)
                    completeCallBack(XMLHttpRequest, textStatus);
                console.log("complete");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                //请求出错处理
                if (!!errorCallBack)
                    errorCallBack(XMLHttpRequest, textStatus, errorThrown);
                console.log("error");
            }
        });
    },
    /**
     * get方法
     * @param url
     * @param data
     * @param successCallBack
     * @param beforeSendCallBack
     * @param completeCallBack
     * @param errorCallBack
     * @param dataFilterCallBack
     * @param dataType
     */
    get: function (url, data, successCallBack, beforeSendCallBack, completeCallBack, errorCallBack, dataFilterCallBack, dataType) {
        this.ajax(url, data, false, successCallBack, beforeSendCallBack, completeCallBack, errorCallBack, dataFilterCallBack, dataType);
    },

    getJson: function (url, data, successCallBack, beforeSendCallBack, completeCallBack, errorCallBack, dataFilterCallBack) {
        this.ajax(url, data, false, successCallBack, beforeSendCallBack, completeCallBack, errorCallBack, dataFilterCallBack, null);
    },
    easyGet: function (url, data, successCallBack, beforeSendCallBack, completeCallBack, dataType) {
        this.ajax(url, data, false, successCallBack, beforeSendCallBack, completeCallBack, null, null, dataType);
    },
    easyGetJson: function (url, data, successCallBack, beforeSendCallBack, completeCallBack) {
        this.ajax(url, data, false, successCallBack, beforeSendCallBack, completeCallBack, null, null, null);
    },
    simpleGet: function (url, data, successCallBack, errorCallBack, dataType) {
        this.ajax(url, data, false, successCallBack, null, null, errorCallBack, null, dataType);
    },
    simpleGetJson: function (url, data, successCallBack, errorCallBack) {
        this.ajax(url, data, false, successCallBack, null, null, errorCallBack, null, null);
    },

    post: function (url, data, successCallBack, beforeSendCallBack, completeCallBack, errorCallBack, dataFilterCallBack, dataType) {
        this.ajax(url, data, true, successCallBack, beforeSendCallBack, completeCallBack, errorCallBack, dataFilterCallBack, dataType);
    },
    postJson: function (url, data, successCallBack, beforeSendCallBack, completeCallBack, errorCallBack) {
        this.ajax(url, data, true, successCallBack, beforeSendCallBack, completeCallBack, errorCallBack, null, null);
    },
    easyPost: function (url, data, successCallBack, beforeSendCallBack, completeCallBack, dataType) {
        this.ajax(url, data, true, successCallBack, beforeSendCallBack, completeCallBack, null, null, dataType);
    },
    easyPostJson: function (url, data, successCallBack, beforeSendCallBack, completeCallBack) {
        this.ajax(url, data, true, successCallBack, beforeSendCallBack, completeCallBack, null, null, null);
    },
    simplePost: function (url, data, successCallBack, errorCallBack, dataType) {
        this.ajax(url, data, true, successCallBack, null, null, errorCallBack, null, dataType);
    },
    simplePostJson: function (url, data, successCallBack, errorCallBack) {
        this.ajax(url, data, true, successCallBack, null, null, errorCallBack, null, null);
    }
};

/**
 * 杨振宇的自定义ajax封装类
 *
 * @param url 请求地址
 * @param data 请求参数
 * @param isPost 是否是post请求
 * @param successCallBack 成功回调
 * @param beforeSendCallBack 请求前回调
 * @param completeCallBack 请求后回调
 * @param errorCallBack 失败回调
 * @param dataFilterCallBack 数据处理拦截回调
 * @param dataType 返回值参数类型
 * @constructor
 */
function YzyAjax(url, data, isPost, successCallBack, beforeSendCallBack, completeCallBack, errorCallBack, dataFilterCallBack, dataType) {
    var type = Boolean(isPost) ? "POST" : "GET";
    var _dataType = !dataType ? "JSON" : dataType;
    $.ajax({
        url: url,    //请求的url地址
        dataType: _dataType,   //返回格式为json
        async: true, //请求是否异步，默认为异步，这也是ajax重要特性
        data: data,    //参数值
        cache: false,
        type: type,   //请求方式
        beforeSend: function (XMLHttpRequest) {
            //请求前的处理
            if (!!beforeSendCallBack)
                beforeSendCallBack(XMLHttpRequest);
            console.log("beforeSend");
        },
        dataFilter: function (data, dataType) {
            //请求的数据进行改装
            if (!!dataFilterCallBack)
                dataFilterCallBack(data, dataType)
            console.log("dataFilter");
            return data;
        },
        success: function (data, textStatus) {
            //请求成功时处理
            if (!!successCallBack)
                successCallBack(data, textStatus);
            console.log("success");
        },
        complete: function (XMLHttpRequest, textStatus) {
            //请求完成的处理
            if (!!completeCallBack)
                completeCallBack(XMLHttpRequest, textStatus);
            console.log("complete");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //请求出错处理
            if (!!errorCallBack)
                errorCallBack(XMLHttpRequest, textStatus, errorThrown);
            console.log("error");
        }
    });
}