-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=io.openliberty.xmlWS-4.0
visibility=public
singleton=true
IBM-App-ForceRestart: uninstall, \
 install
IBM-ShortName: xmlWS-4.0
Subsystem-Name: Jakarta XML Web Services 4.0
IBM-API-Package: \
 jakarta.jws; type="spec", \
 jakarta.jws.soap; type="spec", \
 jakarta.xml.soap; type="spec", \
 jakarta.xml.ws; type="spec", \
 jakarta.xml.ws.handler; type="spec", \
 jakarta.xml.ws.handler.soap; type="spec", \
 jakarta.xml.ws.http; type="spec", \
 jakarta.xml.ws.soap; type="spec", \
 jakarta.xml.ws.spi; type="spec", \
 jakarta.xml.ws.spi.http; type="spec", \
 jakarta.xml.ws.wsaddressing; type="spec", \
 jakarta.annotation; type="spec", \
 jakarta.annotation.security; type="spec", \
 jakarta.annotation.sql; type="spec"
IBM-SPI-Package: \
 com.ibm.wsspi.adaptable.module, \
 com.ibm.ws.adaptable.module.structure, \
 com.ibm.wsspi.adaptable.module.adapters, \
 com.ibm.wsspi.artifact, \
 com.ibm.wsspi.artifact.factory, \
 com.ibm.wsspi.artifact.factory.contributor, \
 com.ibm.wsspi.artifact.overlay, \
 com.ibm.wsspi.artifact.equinox.module, \
 com.ibm.wsspi.anno.classsource, \
 com.ibm.wsspi.anno.info, \
 com.ibm.wsspi.anno.service, \
 com.ibm.wsspi.anno.targets, \
 com.ibm.wsspi.anno.util, \
 com.ibm.ws.anno.classsource.specification
-features=com.ibm.websphere.appserver.eeCompatible-10.0; ibm.tolerates:="11.0, 12.0", \
  io.openliberty.xmlws4.0.internal.ee-10.0; ibm.tolerates:="11.0, 12.0", \
  io.openliberty.xmlws.internal.server-4.0, \
  io.openliberty.xmlBinding-4.0
kind=ga
edition=base
WLP-AlsoKnownAs: jaxws-4.0
WLP-Activation-Type: parallel
WLP-InstantOn-Enabled: true
WLP-Platform: jakartaee-10.0,jakartaee-11.0,jakartaee-12.0
