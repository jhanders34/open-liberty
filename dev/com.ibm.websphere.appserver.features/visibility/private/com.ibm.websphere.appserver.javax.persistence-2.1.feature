-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=com.ibm.websphere.appserver.javax.persistence-2.1
singleton=true
IBM-Process-Types: server, \
 client
-bundles=com.ibm.ws.javaee.persistence.api.2.1, \
  com.ibm.ws.javaee.persistence.2.1
-jars=com.ibm.websphere.javaee.persistence.2.1; location:=dev/api/spec/; mavenCoordinates="org.eclipse.persistence:javax.persistence:2.1.0"
kind=ga
edition=core
