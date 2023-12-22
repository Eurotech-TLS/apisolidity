FROM tomcat:8.0
WORKDIR ../target
RUN cp -a *.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
