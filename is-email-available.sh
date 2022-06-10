#!/bin/bash

if [ -z $1 ]; then
  echo "Ivalid usage, you must provide an email address!"
  echo "Example: $0 tester99@email.com"
  exit 1
fi

 if [ $(./is-this-thing-on.sh project1-db) -eq 0 ]; then
   echo "Cannot connect to database. Check to see if it id running."
   exit 1
 fi


#winpty docker exec -it project1-db psql -U postgres -h localhost -c | grep $1 | wc -w | "select * from store_app.app_users where email = '$1';"

query="select * from store_app.app_users where email = '$1';"

echo "$query" | docker exec -i project1-db psql -U postgres | grep $1 | wc -l

