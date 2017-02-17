#!/bin/bash

#This script automate the build using mavin and
#deploy 

FILE_DIR=$(cd $(dirname ${BASH_SOURCE[0]}) && pwd)


#Build the project using maven
mvn_build()
{
  echo ::::::::::::::::::
	echo :: Maven Build :::
  echo ::::::::::::::::::
	mvn -Dmaven.test.skip=true clean install
	mvn package
}


#Remove previous docker project instance 
docker_clean()
{
  echo :::::::::::::::::::::::::::::::::::::::::::::::::
	echo :: Removing previous docker instance if exists ::
  echo :::::::::::::::::::::::::::::::::::::::::::::::::
  echo 
	docker kill contaAzul-nasa-robot 2> /dev/null
	docker rm contaAzul-nasa-robot 2> /dev/null
}

#Build docker image
docker_build()
{
  echo :::::::::::::::::::::::::::::::::
  echo :: Building docker image named ::
  echo :::::::::::::::::::::::::::::::::
  echo 
	docker build -t nasa-robot .
} 

#Start docker image
docker_run()
{
  
  echo ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
  echo :: Running docker instance named contaAzul-nasa-robot ::
  echo ::::::::::::::::::::::::::::::::::::::::::::::::::::::::
  echo 
	docker run --name contaAzul-nasa-robot -i -t -d -p  8080:8080 nasa-robot
}


# The main execution
# Below will be executed all build/run process

mvn_build
docker_clean
docker_build
docker_run

echo ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
echo :: ContaAzul challenge Server is running sucessfully in http://localhost:8080 ::
echo ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
echo
echo ::::::::::::::::::::::::::::::::::::::::::::
echo :: Wait some seconds to start use it. \;P ::
echo ::::::::::::::::::::::::::::::::::::::::::::
