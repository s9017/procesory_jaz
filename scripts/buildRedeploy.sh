#!/bin/sh

echo "************ UNDEPLOYING *******************"
asadmin undeploy procesory_jaz
echo "************ BUILDING **********************"
mvn package
echo "************ DEPLOYING *********************"
asadmin deploy target/procesory_jaz.war
