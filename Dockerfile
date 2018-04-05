FROM jboss/wildfly
MAINTAINER Sascha Scherrer

ADD target/blogroulette.war /opt/jboss/wildfly/standalone/deployments/

