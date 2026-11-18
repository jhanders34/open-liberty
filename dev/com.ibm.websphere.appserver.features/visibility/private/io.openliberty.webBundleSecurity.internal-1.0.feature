-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=io.openliberty.webBundleSecurity.internal-1.0
WLP-DisableAllFeatures-OnConflict: false
visibility=private
-features=io.openliberty.servlet.internal-3.0; ibm.tolerates:="3.1,4.0,5.0,6.0,6.1,6.2", \
  io.openliberty.webBundle.internal-1.0, \
  io.openliberty.webBundleSecurityCore.internal-1.0
kind=ga
edition=core
