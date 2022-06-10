#!/bin/bash

containerName=$1
dbUsername=$2

if [ -z $containerName ] || [ -z $dbUsername ]; then

  echo "Invalid usage. Expected usage: $0 [container-name] [db-username]"
  echo "Examples: $0 project1-db postgres"
  exit 1
fi

isRunning=$(./is-this-thing-on.sh $containerName)
if ! [ $isRunning ]; then
  echo "Cannnot connect to container with name, $containerName. Container is not running."
  echo "Start the container by running: docker start $containerName"
  exit 1
fi

docker exec -it $containerName psql -U $dbUsername
