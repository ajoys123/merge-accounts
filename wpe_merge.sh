#!/bin/sh -e
if [ -z $1 ]
then
echo Please provide an input csv file
pwd
exit 1
fi


if [ -z $2 ]
then
echo Please provide an output csv file
pwd
exit 1
fi

./gradlew run --args="$1 $2"