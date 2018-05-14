/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validation()
{
    var email = document.getElementById('email');
    var pass = document.getElementById('password');
    var filter_email = /^([a-zA-Z0-9_\.\-])+\@(git.org.in)$/;

    if (!filter_email.test(email.value))
    {
        alert("Enter Valid Email Address");
        email.focus();
        return false;
    }
    if (!((pass.value.length) > 7 && (pass.value.length) < 15)) {
        alert('Password must be between 8 to 14 characters only');
        pass.focus();
        return false;
    }

}

