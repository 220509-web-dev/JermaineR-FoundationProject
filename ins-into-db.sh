#!/bin/bash


 isDataBaseOn=$(./is-this-thing-on.sh project1-db)
 if [ $isDataBaseOn -eq 0 ]; then
   echo "cannot connect to database. Check to see if it is running."
   echo "Cancelling registration."
   exit 1
 fi

echo "Please enter the following information"
    read -p "First name: " fName;
    read -p "Last name: " lName;
    read -p "Email: " email;
    read -p "Username: " uName;
    read -p "Password: " pWord;
    read -r -p "Role " role;

    # Check to see if the provided email is already taken in the database
 emailTaken=$(./is-email-available.sh $email)

# If it is, have the user provide a different email (loop until an available one is given)
 while [ $emailTaken -ne 0 ]; do
   echo "A user is already registered with that email, please enter another or type \q to cancel"
   read -r -p "Email address: " email

   if [ $email = "\q" ]; then
     echo "Cancelling registration"
     exit 0
   else
     emailTaken=$(./is-email-available.sh $email)
   fi

 done

# Check to see if the provided username is already taken in the database
usernameTaken=$(./is-username-available.sh $uName)

 #If it is, have the user provide a different username (loop until an available one is given)
while [ $usernameTaken -ne 0 ]; do
  echo "A user is already registered with that username, please choose another"
  read -p "Username: " uName
  usernameTaken=$(./is-username-available.sh $uName)
done

winpty docker exec -it project1-db psql -U postgres -h localhost -c "insert into store_app.app_users (first_name, last_name, email, username, password, role_id) values ('$fName', '$lName', '$email', '$uName', '$pWord', '$role');"

# echo "$insert" | winpty docker exec -it project1-db psql -U postgres -h localhost -c 