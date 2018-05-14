/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validation()
{
    var contact = document.getElementById('contact');
    var email = document.getElementById('email');
    var pass = document.getElementById('password');
    var repass = document.getElementById('repassword');
    var bmonth = document.getElementById('b_mon');
    var dept = document.getElementById('dept');
    var desg = document.getElementById('desg');
    var filter_contact = /^[6-9]+\d{9}/;
    var filter_email = /^([a-zA-Z0-9_\.\-])+\@(git.org.in)$/;

    if (!filter_email.test(email.value))
    {
        alert('Enter valid email address');
        email.focus();
        return false;
    }

    if (!((pass.value.length) > 7 && (pass.value.length) < 15)) {
        alert('Password must be between 8 to 14 characters only');
        pass.focus();
        return false;
    }

    if (!((pass.value) == (repass.value))) {
        alert('Password and Confirm Password does not match');
        pass.focus();
        return false;
    }

    if ((bmonth.value) == '0') {
        alert('Birthday Month not selected');
        bmonth.focus();
        return false;
    }

    if ((dept.value) == '0_0') {
        alert('Department not selected');
        dept.focus();
        return false;
    }

    if ((desg.value) == '0_0') {
        alert('Designation not selected');
        desg.focus();
        return false;
    }

    if ((contact.value.length) > 0) {
        if (!filter_contact.test(contact.value))
        {
            alert('Enter 10 digit Mobile Number');
            contact.focus();
            return false;
        }
    }

    return true;
}