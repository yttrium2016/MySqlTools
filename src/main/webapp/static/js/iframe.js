(function ($) {
    $.AutoiFrame = function (dom) {
        var iframe = document.getElementById(dom);
        try {
            var height = iframe.contentWindow.document.body.scrollHeight;
            iframe.height = height;
//            console.log(height);
        } catch (ex) {
        }
    }
})(jQuery);