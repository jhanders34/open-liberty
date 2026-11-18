-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=io.openliberty.xmlws.internal.server-4.0
visibility=private
singleton=true
IBM-API-Package:  \
 org.apache.cxf.binding.soap.wsdl.extensions;type="internal", \
 org.apache.cxf.databinding;type="internal"
-features=com.ibm.websphere.appserver.eeCompatible-10.0; ibm.tolerates:="11.0, 12.0", \
  io.openliberty.servletCore.internal-6.0; ibm.tolerates:="6.1, 6.2", \
  io.openliberty.xmlws.common-4.0
-bundles=\
 com.ibm.ws.javaee.ddmodel.ws, \
 com.ibm.ws.jaxws.2.3.common.jakarta;start-phase:=CONTAINER_LATE, \
 com.ibm.ws.jaxws.webcontainer.jakarta, \
 com.ibm.ws.jaxws.web.jakarta, \
 com.ibm.ws.jaxws.wsat, \
 com.ibm.ws.webservices.javaee.common.jakarta
# These jars are used for the xmlWS scripts below.
-jars=\
 io.openliberty.com.sun.xml.messaging.saaj.2.0, \
 io.openliberty.jakarta.xmlBinding.3.0; location:="dev/api/spec/", \
 io.openliberty.jakarta.activation.2.0; location:="dev/api/spec/", \
 io.openliberty.jakarta.jws.3.0; location:="dev/api/spec/", \
 io.openliberty.jakarta.soap.2.0; location:="dev/api/spec/", \
 io.openliberty.jakarta.xmlWS.3.0; location:="dev/api/spec/", \
 io.openliberty.xmlWS.3.0.internal.tools
-files=\
 bin/xmlWS/wsgen; ibm.executable:=true; ibm.file.encoding:=ebcdic, \
 bin/xmlWS/wsimport; ibm.executable:=true; ibm.file.encoding:=ebcdic, \
 dev/api/ibm/schema/ibm-ws-bnd_1_0.xsd, \
 bin/xmlWS/wsimport.bat, \
 bin/xmlWS/tools/ws-wsimport.jar, \
 bin/xmlWS/wsgen.bat, \
 bin/xmlWS/tools/ws-wsgen.jar
kind=ga
edition=base
WLP-Activation-Type: parallel
