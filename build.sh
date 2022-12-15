mvn clean
mvn package
docker build -t lionani07/lio:helpdesk.3.0 .
docker push lionani07/lio:helpdesk.3.0