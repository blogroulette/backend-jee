FROM jboss/wildfly

ADD target/blogroulette-jee.war /opt/jboss/wildfly/standalone/deployments/
