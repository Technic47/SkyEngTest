FROM tomcat:latest
MAINTAINER Pavel Kuznetsov
EXPOSE 9090
COPY target/SkyEng-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/app.war

CMD ["catalina.sh","run"]