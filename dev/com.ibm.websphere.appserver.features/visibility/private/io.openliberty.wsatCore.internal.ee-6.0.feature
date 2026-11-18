-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=io.openliberty.wsatCore.internal.ee-6.0
singleton=true
visibility = private
WLP-DisableAllFeatures-OnConflict: false
-features=\
  io.openliberty.servletCore.internal-3.0; ibm.tolerates:="3.1,4.0", \
  io.openliberty.xmlws.internal.server-2.2
-bundles=\
  com.ibm.ws.wsat.common; start-phase:=CONTAINER_LATE, \
  com.ibm.ws.wsat.webclient, \
  com.ibm.ws.wsat.webservice
kind=ga
edition=base
