-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=io.openliberty.xmlws.internal.server-3.0
visibility=private
singleton=true
IBM-API-Package:  \
 org.apache.cxf.binding.soap.wsdl.extensions;type="internal", \
 org.apache.cxf.databinding;type="internal"
-features=com.ibm.websphere.appserver.eeCompatible-9.0, \
  io.openliberty.servletCore.internal-5.0, \
  com.ibm.websphere.appserver.globalhandler-2.0, \
  io.openliberty.xmlws.common-3.0
-bundles=\
 com.ibm.ws.javaee.ddmodel.ws, \
 com.ibm.ws.jaxws.2.3.common.jakarta;start-phase:=CONTAINER_LATE, \
 com.ibm.ws.jaxws.webcontainer.jakarta, \
 com.ibm.ws.jaxws.web.jakarta, \
 com.ibm.ws.jaxws.wsat, \
 com.ibm.ws.webservices.javaee.common.jakarta, \
 io.openliberty.jaxws.globalhandler.internal.jakarta
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
