window.onload = function() {
    document.getElementById("login-btn").addEventListener("click", login);
}

function login() {

    // Convenience references
    let usernameInput = document.getElementById('userName');
    let passwordInput = document.getElementById('password');
    let errorContainer = document.getElementById('error-message');

    let userName = usernameInput.value;
    let password = passwordInput.value;

    if (userName && password) {

        // If the error message is being displayed, hide it
        errorContainer.setAttribute('hidden', true);


        let respData = fetch('/helpDesk/auth', {
            method: 'POST',
            body: JSON.stringify({userName, password})
        }).then(resp => {
            console.log(`Response status: ${resp.status}`);
            console.log(`Response timestamp: ${Date.now()}`);

            if (resp.status != 200) {
                errorContainer.removeAttribute('hidden');
                errorContainer.innerText = "Login failed!";
                return;
            }

            return resp.json();
        })

        if (respData) {
            respData.then(data => {
                let successMsgContainer = document.createElement('p');
                successMsgContainer.setAttribute('class', 'alert alert-success');
                successMsgContainer.innerText = `Login successful! Welcome, ${data['firstName']}!`;
                document.getElementById('login-btn').appendChild(successMsgContainer);
            });
        }

    } else {

        // Show the error message
        errorContainer.removeAttribute('hidden');
        errorContainer.innerText = "You must provide a username and password!";

    }

}

