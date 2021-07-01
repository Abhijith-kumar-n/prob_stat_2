/*function loginSubmit(){
    var username = document.getElementById('username');
    var password = document.getElementById('password');

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:9090/user/allusers";
    xhr.open("GET", url, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log(xhr.responseText);
            console.log(this.responseText);
            const response = JSON.parse(my_response);
            console.log(response);
        }
        xhr.send(null);
    }
}


 */
/*
function validate()

{

    var username = document.getElementById("username").value;

    var password = document.getElementById("password").value;

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:9090/user/allusers";
    xhr.open("GET", url, true);
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            alert(this.responseText);
    };

    var data = JSON.stringify({ "firstname": username, "lastname": password});

    alert(data);

    console.log(data);

    xhr.send(data);

    }

}

 */
// Example POST method implementation:
async function postData(url = '', data = {}) {
    // Default options are marked with *
    const response = await fetch(url, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        redirect: 'follow', // manual, *follow, error
        referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
        body: JSON.stringify(data) // body data type must match "Content-Type" header
    });
    return response.json(); // parses JSON response into native JavaScript objects
}

postData('http://localhost:9090/user/password', {"userid":null, "userName":username, "password":password})
    .then(data => {
        console.log(data); // JSON data parsed by `data.json()` call
    });
