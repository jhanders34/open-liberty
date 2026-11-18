-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=io.openliberty.wsAtomicTransaction.internal-1.2
WLP-DisableAllFeatures-OnConflict: false
visibility=private
Manifest-Version: 1.0
-features=com.ibm.websphere.appserver.transaction-1.2; ibm.tolerates:="1.1, 2.0, 2.1", \
  io.openliberty.webBundleSecurityCore.internal-1.0, \
  io.openliberty.servletCore.internal-3.0; ibm.tolerates:="3.1,4.0,5.0,6.0,6.1,6.2", \
  io.openliberty.xmlws.internal.server-2.2; ibm.tolerates:="3.0, 4.0", \
  io.openliberty.wsatCore.internal.ee-6.0; ibm.tolerates:="9.0"
kind=ga
edition=base
