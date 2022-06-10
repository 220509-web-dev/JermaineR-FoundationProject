window.onload = function () {
    document.getElementById("signup-btn").addEventListener("click", signup);
  }
  function signup() {

      // Convenience references
      let usernameInput = document.getElementById('userName');
      let passwordInput = document.getElementById('password');
      let emailInput = document.getElementById('email');
      let firstNameInput = document.getElementById('firstName');
      let lastNameInput = document.getElementById('lastName');
      let errorContainer = document.getElementById('error-message');
      let successContainer = document.getElementById('success-message');

      let userName = usernameInput.value;
      let password = passwordInput.value;
      let email = emailInput.value;
      let firstName = firstNameInput.value;
      let lastName = lastNameInput.value;


      if (userName && password && email) {

          // If the error message is being displayed, hide it
          errorContainer.setAttribute('hidden', true);
          successContainer.setAttribute('hidden', true);


          let respData = fetch('/helpDesk/users', {
              method: 'POST',
              body: JSON.stringify({userName, password, email, firstName, lastName })
          }).then(resp => {
              console.log(`Response status: ${resp.status}`);
              console.log(`Response timestamp: ${Date.now()}`);

              if (respData && resp.status != 204) {
                respData.then(data => {
                  errorContainer.removeAttribute('hidden');
                  errorContainer.innerText = `${data['message']}!`;
                });

              }


                if (resp.status = 204) {

                        successContainer.removeAttribute('hidden');
                        successContainer.innerText = `Sign up successful!`;

                }

                return resp.json();
          })



      } else {

          // Show the error message
          errorContainer.removeAttribute('hidden');
          errorContainer.innerText = "You must provide a username, password, and email!";

      }

  }

//function signup() {
//  console.log("signup");
//  let userName = document.getElementById("userName").value;
//  let password = document.getElementById("password").value;
//  let email = document.getElementById("email").value;
//
//  fetch("/helpDesk/users", {
//    method: "POST",
//    body: JSON.stringify({userName,password,email})});
//
//  alert("You have now registered for HelpDesk!");
