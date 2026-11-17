-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=io.openliberty.servletCore.internal-3.1
WLP-DisableAllFeatures-OnConflict: false
visibility=private
singleton=true
IBM-App-ForceRestart: install, uninstall
Subsystem-Category: JavaEE7Application
-features=io.openliberty.servlet.api-3.1, \
  com.ibm.websphere.appserver.javaeeddSchema-1.0, \
  com.ibm.websphere.appserver.eeCompatible-6.0; ibm.tolerates:="7.0", \
  com.ibm.websphere.appserver.httptransport-1.0, \
  com.ibm.websphere.appserver.requestProbes-1.0, \
  com.ibm.websphere.appserver.javax.annotation-1.2; ibm.tolerates:="1.1", \
  com.ibm.websphere.appserver.injection-1.0, \
  com.ibm.websphere.appserver.javaeePlatform-7.0
-bundles=com.ibm.ws.app.manager.war, \
 com.ibm.ws.managedobject, \
 com.ibm.ws.org.apache.commons.io, \
 com.ibm.websphere.security, \
 com.ibm.ws.org.apache.commons.fileupload, \
 com.ibm.ws.webcontainer.servlet.3.1, \
 com.ibm.ws.webcontainer.servlet.3.1.factories, \
 com.ibm.ws.session, \
 com.ibm.ws.webcontainer, \
 com.ibm.ws.webcontainer.cors, \
 com.ibm.ws.http.plugin.merge, \
 com.ibm.ws.webserver.plugin.runtime, \
 com.ibm.ws.webserver.plugin.runtime.interfaces
-jars=com.ibm.ws.webserver.plugin.utility
-files=bin/tools/ws-webserverPluginutil.jar, \
 bin/pluginUtility; ibm.executable:=true; ibm.file.encoding:=ebcdic, \
 bin/pluginUtility.bat
kind=ga
edition=core
WLP-Activation-Type: parallel
