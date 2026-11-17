-include= ~${workspace}/cnf/resources/bnd/feature.props
symbolicName=com.ibm.websphere.appserver.servlet-3.1
WLP-DisableAllFeatures-OnConflict: false
visibility=public
singleton=true
IBM-App-ForceRestart: install, uninstall
IBM-ShortName: servlet-3.1
IBM-API-Package: javax.servlet.annotation;  type="spec", \
 javax.servlet.descriptor;  type="spec", \
 javax.servlet.resources;  type="spec", \
 javax.servlet.http;  type="spec", \
 javax.servlet;  type="spec", \
 com.ibm.websphere.servlet.session;  type="ibm-api", \
 com.ibm.wsspi.servlet.session;  type="ibm-api", \
 com.ibm.websphere.servlet.error;  type="ibm-api", \
 com.ibm.websphere.servlet.container;  type="ibm-api", \
 com.ibm.websphere.servlet.context;  type="ibm-api", \
 com.ibm.websphere.servlet.event;  type="ibm-api", \
 com.ibm.websphere.webcontainer;  type="ibm-api", \
 com.ibm.wsspi.webcontainer;  type="internal", \
 com.ibm.wsspi.webcontainer.annotation;  type="internal", \
 com.ibm.websphere.endpoint; type="ibm-api", \
 javax.annotation; type="spec", \
 javax.annotation.security; type="spec", \
 javax.annotation.sql; type="spec"
IBM-SPI-Package: com.ibm.wsspi.webcontainer, \
 com.ibm.wsspi.webcontainer.collaborator, \
 com.ibm.wsspi.webcontainer.extension, \
 com.ibm.wsspi.webcontainer.filter, \
 com.ibm.wsspi.webcontainer.metadata, \
 com.ibm.wsspi.webcontainer.osgi.extension, \
 com.ibm.wsspi.webcontainer.servlet, \
 com.ibm.wsspi.webcontainer.webapp, \
 com.ibm.websphere.servlet.filter, \
 com.ibm.websphere.servlet.response, \
 com.ibm.ws.webcontainer.extension, \
 com.ibm.websphere.servlet.request, \
 com.ibm.ws.webcontainer.spiadapter.collaborator, \
 com.ibm.websphere.webcontainer.async, \
 com.ibm.wsspi.adaptable.module, \
 com.ibm.ws.adaptable.module.structure, \
 com.ibm.wsspi.adaptable.module.adapters, \
 com.ibm.wsspi.artifact, \
 com.ibm.wsspi.artifact.factory, \
 com.ibm.wsspi.artifact.factory.contributor, \
 com.ibm.wsspi.artifact.overlay, \
 com.ibm.wsspi.artifact.equinox.module, \
 com.ibm.wsspi.http, \
 com.ibm.wsspi.http.ee8, \
 com.ibm.wsspi.anno.classsource, \
 com.ibm.wsspi.anno.info, \
 com.ibm.wsspi.anno.service, \
 com.ibm.wsspi.anno.targets, \
 com.ibm.wsspi.anno.util, \
 com.ibm.ws.anno.classsource.specification
Subsystem-Category: JavaEE7Application
-features=com.ibm.websphere.appserver.eeCompatible-6.0; ibm.tolerates:="7.0", \
  io.openliberty.servletCore.internal-3.1, \
  com.ibm.websphere.appserver.servlet-servletSpi1.0
-jars=com.ibm.websphere.appserver.api.servlet; location:=dev/api/ibm/
-files=dev/api/ibm/javadoc/com.ibm.websphere.appserver.api.servlet_1.1-javadoc.zip
Subsystem-Name: Java Servlets 3.1
kind=ga
edition=core
WLP-Activation-Type: parallel
WLP-Platform: javaee-7.0
