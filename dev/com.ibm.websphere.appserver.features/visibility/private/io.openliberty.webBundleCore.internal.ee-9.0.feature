-include= ~${workspace}/cnf/resources/bnd/feature.props

symbolicName = io.openliberty.webBundleCore.internal.ee-9.0
singleton=true
visibility = private

-features= io.openliberty.servletCore.internal-5.0; ibm.tolerates:="6.0, 6.1, 6.2"

-bundles= com.ibm.ws.app.manager.wab.jakarta; start-phase:=APPLICATION_EARLY

kind=ga
edition=core
WLP-Activation-Type: parallel
