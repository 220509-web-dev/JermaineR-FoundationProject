#!/bin/bash

# Output:
# - 0, means "no, this thing is not on"
# - any onther value, means "this thing in on"

# Make sure that a container name was provided as the first argument
if [ -z $1 ]; then
  echo "Indvalid usage, you must provide a container name!"
  echo "Expected usage : $0 my-container"
  exit 1
fi

# Breakdown:
# - List all the running containers using 'docker ps'
#  -  search the list of running containers for the provided container
#  - Count the number of lines output by 'grep' using 'wc'
docker ps | grep $1 | wc -l
