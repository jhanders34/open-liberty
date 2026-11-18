-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=io.openliberty.wsatCore.internal.ee-9.0
singleton=true
visibility = private
-features=\
  io.openliberty.servletCore.internal-5.0; ibm.tolerates:="6.0,6.1,6.2", \
  io.openliberty.xmlws.internal.server-3.0; ibm.tolerates:="4.0"
-bundles=\
  com.ibm.ws.wsat.common.jakarta; start-phase:=CONTAINER_LATE, \
  com.ibm.ws.wsat.webclient.jakarta, \
  com.ibm.ws.wsat.webservice.jakarta
kind=ga
edition=base
