/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validation() {
    var sem = document.getElementById('sem');

    if ((sem.value) == '0_0') {
        alert('Semester not selected');
        sem.focus();
        return false;
    }
}

function refresh() {
    var sem = document.getElementById('sem');
    var url = window.location.href;
    location.href=url+"?sem="+sem;
}
