#!/bin/sh

echo "Starting buy more API...."
if [ "$DEBUG_MODE" ]; then
  echo "Starting in DEBUG_MODE at port: $DEBUG_PORT"
  java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:"$DEBUG_PORT" -jar projeto-final-backend*.jar
else
  java -jar projeto-final-backend*.jar
fi