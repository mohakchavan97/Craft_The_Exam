/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function myFunction() {
    var x = document.getElementById("mySelect").value;
    var url = 'file:///F:/NetBeans%20Projects/Craft_THE_Exam/src/java/auto.html?name=' + x;
    document.location.href = url;
    location.reload(true);
}
window.onload = function () {
    var url = document.location.href, params = url.split('?')[1].split('&'), data = {}, tmp;
    for (var i = 0, l = params.length; i < l; i++) {
        tmp = params[i].split('=');
        data[tmp[0]] = tmp[1];
    }

    document.getElementById(data.name).hidden = false;
};