#!/bin/bash

echo "select * from store_app.app_users;" | docker exec -i project1-db psql -U postgres --csv | tee result.csv
