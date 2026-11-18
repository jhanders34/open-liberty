-include= ~${workspace}/cnf/resources/bnd/feature.props

symbolicName = io.openliberty.webBundle.internal.ee-10.0
singleton=true
visibility = private

-features= io.openliberty.servlet.internal-6.0; ibm.tolerates:="6.1, 6.2"

kind=ga
edition=core
WLP-Activation-Type: parallel
