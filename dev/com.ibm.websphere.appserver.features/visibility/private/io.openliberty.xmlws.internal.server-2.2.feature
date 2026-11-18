-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=io.openliberty.xmlws.internal.server-2.2
WLP-DisableAllFeatures-OnConflict: false
visibility=private
singleton=true
WLP-Activation-Type: parallel
IBM-API-Package:  \
 org.apache.cxf.binding.soap.wsdl.extensions;type="internal", \
 org.apache.cxf.databinding;type="internal"
Subsystem-Name: Java Web Services 2.2
-features=\
  io.openliberty.servlet.api-3.0; apiJar=false; ibm.tolerates:="3.1,4.0", \
  com.ibm.websphere.appserver.globalhandler-1.0, \
  com.ibm.websphere.appserver.httpcommons-1.0, \
  com.ibm.websphere.appserver.injection-1.0, \
  com.ibm.websphere.appserver.internal.optional.jaxb-2.2, \
  com.ibm.websphere.appserver.internal.slf4j-1.7, \
  com.ibm.websphere.appserver.internal.cxf.common-3.2, \
  com.ibm.websphere.appserver.internal.optional.jaxws-2.2, \
  com.ibm.websphere.appserver.javax.mail-1.5; ibm.tolerates:="1.6"
-bundles=\
 com.ibm.ws.javaee.ddmodel.ws, \
 com.ibm.ws.jaxws.wsat, \
 com.ibm.ws.jaxws.2.3.common; start-phase:=CONTAINER_LATE, \
 com.ibm.ws.webservices.javaee.common, \
 com.ibm.websphere.javaee.jws.1.0; require-java:="9"; location:="dev/api/spec/,lib/"; mavenCoordinates="javax.jws:jsr181-api:1.0-MR1", \
 com.ibm.websphere.javaee.jaxws.2.2; location:="dev/api/spec/,lib/"; mavenCoordinates="javax.xml.ws:jaxws-api:2.2.12", \
 com.ibm.websphere.javaee.wsdl4j.1.2; location:="dev/api/spec/,lib/"; mavenCoordinates="wsdl4j:wsdl4j:1.6.3", \
 com.ibm.websphere.prereq.wsdl4j.api; location:="dev/api/spec/,lib/", \
 com.ibm.ws.com.sun.xml.messaging.saaj; require-java:="9", \
 com.ibm.ws.javaee.ddmodel.wsbnd, \
 com.ibm.ws.jaxws.tools.2.2.10, \
 com.ibm.ws.org.apache.cxf.cxf.rt.bindings.soap.3.2, \
 com.ibm.ws.org.apache.cxf.cxf.rt.bindings.xml.3.2, \
 com.ibm.ws.org.apache.cxf.cxf.rt.databinding.jaxb.3.2, \
 com.ibm.ws.org.apache.cxf.cxf.rt.features.logging.3.2, \
 com.ibm.ws.org.apache.cxf.cxf.rt.frontend.jaxws.3.2, \
 com.ibm.ws.org.apache.cxf.cxf.rt.frontend.simple.3.2, \
 com.ibm.ws.org.apache.cxf.cxf.rt.management.3.2, \
 com.ibm.ws.org.apache.cxf.cxf.tools.common.3.2, \
 com.ibm.ws.org.apache.cxf.cxf.tools.validator.3.2, \
 com.ibm.ws.org.apache.cxf.cxf.rt.wsdl.3.2, \
 com.ibm.ws.org.apache.cxf.cxf.rt.ws.addr.3.2, \
 com.ibm.ws.org.apache.cxf.cxf.rt.ws.policy.3.2, \
 com.ibm.ws.org.jvnet.mimepull;require-java:="9", \
 com.ibm.ws.prereq.wsdl4j.1.6.2, \
 io.openliberty.jaxws.globalhandler.internal
-files=\
 bin/jaxws/wsgen; ibm.executable:=true; ibm.file.encoding:=ebcdic, \
 bin/jaxws/wsimport; ibm.executable:=true; ibm.file.encoding:=ebcdic, \
 dev/api/ibm/schema/ibm-ws-bnd_1_0.xsd, \
 bin/jaxws/wsimport.bat, \
 bin/jaxws/tools/ws-wsimport.jar, \
 bin/jaxws/wsgen.bat, \
 bin/jaxws/tools/ws-wsgen.jar
kind=ga
edition=base