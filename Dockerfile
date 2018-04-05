FROM jboss/wildfly

ADD target/blogroulette.war /opt/jboss/wildfly/standalone/deployments/

