/*******************************************************************************
 * Copyright (c) 2017, 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse public static License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.websphere.security.audit.context;

import java.util.ArrayList;

import com.ibm.websphere.ras.annotation.Trivial;

/**
 *
 */
public class AuditManager {

    private static ThreadLocal<AuditThreadContext> threadLocal = new ThreadLocal<>();

    // put back once everything is updated to not use the constructor.
    /*private AuditManager() {
        // private constructor.  This class should not be new'd up.
    }*/

    /**
     * Sets the HttpServletRequest on the thread
     */
    public static void setHttpServletRequest(Object req) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setHttpServletRequest(req);
    }

    /**
     * Gets the HttpServletRequest from the thread
     */
    public static Object getHttpServletRequest() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getHttpServletRequest();
    }

    /**
     * Sets the WebRequest on the thread
     */
    public static void setWebRequest(Object webreq) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setWebRequest(webreq);
    }

    /**
     * Gets the WebRequest from the thread
     */
    public static Object getWebRequest() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getWebRequest();
    }

    /**
     * Sets the realm on the thread
     */
    public static void setRealm(String realm) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setRealm(realm);
    }

    /**
     * Gets the realm on the thread
     */
    public static String getRealm() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getRealm();
    }

    /**
     * Sets the JMS conversation on the thread
     */
    public static void setJMSConversationMetaData(Object conversationMetaData) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setJMSConversationMetaData(conversationMetaData);
    }

    /**
     * Gets the REST request on the thread
     */
    public static Object getJMSConversationMetaData() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getJMSConversationMetaData();
    }

    /**
     * Sets the JMS bus name on the thread
     */
    public static void setJMSBusName(String busName) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setJMSBusName(busName);
    }

    /**
     * Gets the JMS bus name on the thread
     */
    public static String getJMSBusName() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getJMSBusName();
    }

    /**
     * Sets whether we are doing a local or remote JMS call
     */
    public static void setJMSCallType(String callType) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setJMSCallType(callType);
    }

    /**
     * Sets the JMS messaging engine on the thread
     */
    public static void setJMSMessagingEngine(String me) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setJMSMessagingEngine(me);
    }

    /**
     * Gets the JMS messaging engine on the thread
     */
    public static String getJMSMessagingEngine() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getJMSMessagingEngine();
    }

    /**
     * Sets the REST request on the thread
     */
    public static void setRESTRequest(Object request) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setRESTRequest(request);
    }

    /**
     * Gets the REST request on the thread
     */
    public static Object getRESTRequest() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getRESTRequest();
    }

    /**
     * Sets the repositoryId on the thread
     */
    public static void setRepositoryId(String repoId) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setRepositoryId(repoId);
    }

    /**
     * Gets the repositoryId on the thread
     */
    public static String getRepositoryId() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getRepositoryId();
    }

    /**
     * Sets the repository uniqueName on the thread
     */
    public static void setRepositoryUniqueName(String uniqueName) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setRepositoryUniqueName(uniqueName);
    }

    /**
     * Gets the repository uniqueName on the thread
     */
    public static String getRepositoryUniqueName() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getRepositoryUniqueName();
    }

    /**
     * Sets the repository realm on the thread
     */
    public static void setRepositoryRealm(String realm) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setRepositoryRealm(realm);
    }

    /**
     * Sets the CRUD request type on the thread
     */
    public static void setRequestType(String requestType) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setRequestType(requestType);;
    }

    /**
     * Sets the credential type of the user on the thread
     */
    public static void setCredentialType(String cred) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setCredentialType(cred);
    }

    /**
     * Sets the user on the thread
     */
    public static void setCredentialUser(String cred) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setCredentialUser(cred);
    }

    /**
     * Sets the remote address on the thread
     */
    public static void setRemoteAddr(String addr) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setRemoteAddr(addr);
    }

    /**
     * Sets the agent type on the thread
     */
    public static void setAgent(String agent) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setAgent(agent);
    }

    /**
     * Sets the local address requested on the thread
     */
    public static void setLocalAddr(String addr) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setLocalAddr(addr);
    }

    /**
     * Sets the local port requested on the thread
     */
    public static void setLocalPort(String port) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setLocalPort(port);
    }

    /**
     * Sets the sessionId on the thread
     */
    public static void setSessionId(String id) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setSessionId(id);
    }

    /**
     * Sets the http type of the request on the thread
     */
    public static void setHttpType(String type) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setHttpType(type);
    }

    /**
     * Gets the repository realm on the thread
     */
    public static String getRepositoryRealm() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getRepositoryRealm();
    }

    /**
     * Gets the CRUD request type on the thread
     */
    public static String getRequestType() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getRequestType();
    }

    /**
     * Gets the credential type of the authenticated user on this thread
     */
    public static String getCredentialType() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getCredentialType();
    }

    /**
     * Gets the authenticated user on this thread
     */
    public static String getCredentialUser() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getCredentialUser();
    }

    /**
     * Gets the remote address from the request on this thread
     */
    public static String getRemoteAddr() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getRemoteAddr();
    }

    /**
     * Gets the agent type on this thread
     */
    public static String getAgent() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getAgent();
    }

    /**
     * Gets the requested local address on this thread
     */
    public static String getLocalAddr() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getLocalAddr();
    }

    /**
     * Gets the requested local port on this thread
     */
    public static String getLocalPort() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getLocalPort();
    }

    /**
     * Gets the sessionId on this thread
     */
    public static String getSessionId() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getSessionId();
    }

    /**
     * Gets the http type on this thread
     */
    public static String getHttpType() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getHttpType();
    }

    /**
     * Sets the list of users from the initial caller through the last caller in a runAs delegation call
     */
    public static void setDelegatedUsers(ArrayList<String> delegatedUsers) {
        AuditThreadContext auditThreadContext = getAuditThreadContext(true);
        auditThreadContext.setDelegatedUsers(delegatedUsers);
    }

    /**
     * Gets the list of users from the initial through the last in a runAs delegation call
     */
    public static ArrayList<String> getDelegatedUsers() {
        AuditThreadContext auditThreadContext = getAuditThreadContext(false);
        return auditThreadContext == null ? null : auditThreadContext.getDelegatedUsers();
    }

    /**
     * Gets the audit thread context that is unique per thread.
     * If/when a common thread storage framework is supplied, then this method
     * implementation may need to be updated to take it into consideration.
     *
     * @return the subject thread context.
     */
    @Trivial
    private static AuditThreadContext getAuditThreadContext(boolean create) {
        ThreadLocal<AuditThreadContext> currentThreadLocal = getThreadLocal();
        AuditThreadContext auditThreadContext = currentThreadLocal.get();
        if (auditThreadContext == null && create) {
            auditThreadContext = new AuditThreadContext();
            currentThreadLocal.set(auditThreadContext);
        }
        return auditThreadContext;
    }

    /**
     * Gets the thread local object.
     * If/when a common thread storage framework is supplied, then this method
     * implementation may need to be updated to take it into consideration.
     *
     * @return the thread local object.
     */
    @Trivial
    private static ThreadLocal<AuditThreadContext> getThreadLocal() {
        return threadLocal;
    }
}
