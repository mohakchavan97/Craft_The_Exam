/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validation() {

    var sub = document.getElementById('subj');

    if ((sub.value) == '0_0') {
        alert('Subject not selected');
        sub.focus();
        return false;
    }
}