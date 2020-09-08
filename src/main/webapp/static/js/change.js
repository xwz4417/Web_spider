$(document).ready(function () {
    $("#f42").click(function () {
        $("div>iframe").attr("src", "http://a1b2c3d4.6699a.xyz:16982/forum.php?mod=forumdisplay&fid=42");
    });
    $("#f2").click(function () {
        $("div>iframe").attr("src", "http://a1b2c3d4.6699a.xyz:16982/forum.php?mod=forumdisplay&fid=2");
    });
$("#submit").click(function () {
var progress_bar=$(".progress-bar");
setTimeout(progress_bar.attr("style","width: 40%"),2000);

});

});