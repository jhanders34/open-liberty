/*******************************************************************************
 * Copyright (c) 2012, 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package com.ibm.ws.messaging.security.authentication.internal;

import java.security.AccessController;
import java.security.cert.Certificate;

import javax.security.auth.Subject;

import com.ibm.websphere.ras.Tr;
import com.ibm.websphere.ras.TraceComponent;
import com.ibm.websphere.security.audit.AuditConstants;
import com.ibm.websphere.security.audit.context.AuditManager;
import com.ibm.ws.messaging.security.MSTraceConstants;
import com.ibm.ws.messaging.security.MessagingSecurityConstants;
import com.ibm.ws.messaging.security.MessagingSecurityException;
import com.ibm.ws.messaging.security.authentication.MessagingAuthenticationException;
import com.ibm.ws.messaging.security.authentication.MessagingAuthenticationService;
import com.ibm.ws.messaging.security.authentication.actions.MessagingLoginAction;
import com.ibm.ws.messaging.security.internal.MessagingSecurityServiceImpl;
import com.ibm.ws.messaging.security.utility.MessagingSecurityUtility;
import com.ibm.ws.security.audit.Audit;
import com.ibm.ws.security.authentication.AuthenticationData;
import com.ibm.ws.security.authentication.WSAuthenticationData;
import com.ibm.ws.sib.jfapchannel.ConversationMetaData;
import com.ibm.ws.sib.utils.ras.SibTr;

/*
 * Assumption:
 * 1. These methods are called only when the security is enabled for Messaging
 * and hence we are not doing any explicit checks inside this code
 * 2. Security Auditing feature does not exist for Liberty profile
 * 3. Check if the user passed has any of the access defined in Messaging, then only proceed for authentication
 * Reference: Configuration Differences between the Full profile and the Liberty profile: Security
 */
/**
 * Implementation class for Messaging Authentication Service
 * 
 * @author Sharath Chandra B
 */
public class MessagingAuthenticationServiceImpl implements
                MessagingAuthenticationService, MessagingSecurityConstants {

    // Trace component for the MessagingAuthenticationService Implementation class
    private static TraceComponent tc = SibTr.register(MessagingAuthenticationServiceImpl.class,
                                                      MSTraceConstants.MESSAGING_SECURITY_TRACE_GROUP,
                                                      MSTraceConstants.MESSAGING_SECURITY_RESOURCE_BUNDLE);

    // Absolute class name along with the package name, used for tracing
    private static final String CLASS_NAME = "com.ibm.ws.messaging.security.authentication.internal.MessagingAuthenticationServiceImpl.";

    private MessagingSecurityServiceImpl _messagingSecurityService = null;

    private final AuthenticationData authenticationDataForSubject = new WSAuthenticationData();

    /**
     * Constructor
     * 
     * @param messagingSecurityService
     */
    public MessagingAuthenticationServiceImpl(
                                              MessagingSecurityServiceImpl messagingSecurityService) {
        if (TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled()) {
            SibTr.entry(tc, CLASS_NAME + "constructor", messagingSecurityService);
        }
        this._messagingSecurityService = messagingSecurityService;
        if (TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled()) {
            SibTr.exit(tc, CLASS_NAME + "constructor");
        }
    }

    @Override
    public Subject login(Subject subj) throws MessagingAuthenticationException {
        if (TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled()) {
            SibTr.entry(tc, CLASS_NAME + "login", subj);
        }

        Subject result = AccessController.doPrivileged(new MessagingLoginAction(
                        authenticationDataForSubject, MessagingSecurityConstants.SUBJECT, _messagingSecurityService.getSecurityService(), subj));
        //114580
        String userName = null;
        if (result == null) {
            try {
                userName = _messagingSecurityService.getUniqueUserName(subj);
            } catch (MessagingSecurityException e) {
                //No FFDC Code Needed
            }
        }

        if (Audit.isAuditRequired(Audit.EventID.SECURITY_JMS_AUTHN_01, result == null ? AuditConstants.FAILURE : AuditConstants.SUCCESS)) {
            String busName = AuditManager.getJMSBusName();
            String messagingEngine = AuditManager.getJMSMessagingEngine();
            String credType = "User subject";
            ConversationMetaData cmd = (ConversationMetaData) AuditManager.getJMSConversationMetaData();
            if (cmd != null) {
                Audit.audit(Audit.EventID.SECURITY_JMS_AUTHN_01, result == null ? userName : subj.getPrincipals().iterator().next().getName(), 
                                cmd.getRemoteAddress().getHostAddress(), Integer.toString(cmd.getRemotePort()),
                            cmd.getChainName(), busName, messagingEngine, credType, Integer.valueOf(result == null ? 201 : 200));
            } else {
                Audit.audit(Audit.EventID.SECURITY_JMS_AUTHN_01, result == null ? userName : subj.getPrincipals().iterator().next().getName(), null, null, null, 
                                busName, messagingEngine, credType, Integer.valueOf(result == null ? 201 : 200));
            }
        }

        if (TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled()) {
            SibTr.exit(tc, CLASS_NAME + "login", result);
        }
        if (result == null) {
            throwAuthenticationException(userName);//114580
        }

        return result;
    }

    @Override
    public Subject login(String userName, String password) throws MessagingAuthenticationException {
        if (TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled()) {
            SibTr.entry(tc, CLASS_NAME + "login", new Object[] { userName, "Password Not Traced" });
        }
        AuthenticationData authData = MessagingSecurityUtility
                        .createAuthenticationData(userName, password);
        Subject result = AccessController.doPrivileged(new MessagingLoginAction(
                        authData, MessagingSecurityConstants.USERID, _messagingSecurityService.getSecurityService()));

        if (Audit.isAuditRequired(Audit.EventID.SECURITY_JMS_AUTHN_01, result == null ? AuditConstants.FAILURE : AuditConstants.SUCCESS)) {
            String busName = AuditManager.getJMSBusName();
            String messagingEngine = AuditManager.getJMSMessagingEngine();
            String credType = "Userid+Password";

            ConversationMetaData cmd = (ConversationMetaData) AuditManager.getJMSConversationMetaData();
            if (cmd != null) {
                Audit.audit(Audit.EventID.SECURITY_JMS_AUTHN_01, userName, cmd.getRemoteAddress().getHostAddress(), Integer.toString(cmd.getRemotePort()),
                            cmd.getChainName(), busName, messagingEngine, credType, Integer.valueOf(result == null ? 201 : 200));
            } else {
                Audit.audit(Audit.EventID.SECURITY_JMS_AUTHN_01, userName, null, null, null, busName, messagingEngine, credType, Integer.valueOf(result == null ? 201 : 200));
            }
        }

        if (TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled()) {
            SibTr.exit(tc, CLASS_NAME + "login", result);
        }

        if (result == null) {
            throwAuthenticationException(userName);//114580
        }

        return result;
    }

    @Override
    public Subject login(byte[] securityToken,
                         String securityTokenType) throws MessagingAuthenticationException {
        if (TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled()) {
            SibTr.entry(tc, CLASS_NAME + "login", new Object[] { securityToken, securityTokenType });
        }
        if (!SUPPORTED_TOKEN_TYPE.equals(securityTokenType)) {
            SibTr.error(tc, "SECURITY_TOKEN_TYPE_NOT_SUPPORTED_MSE1002", securityTokenType);
            if (Audit.isAuditRequired(Audit.EventID.SECURITY_JMS_AUTHN_01, AuditConstants.FAILURE)) {
                String busName = AuditManager.getJMSBusName();
                String messagingEngine = AuditManager.getJMSMessagingEngine();
                String credType = "Token";
                Audit.audit(Audit.EventID.SECURITY_JMS_AUTHN_01, new String(securityToken), AuditManager.getJMSConversationMetaData(), busName, messagingEngine, credType,
                            Integer.valueOf(201));
            }
            throw new MessagingAuthenticationException(Tr.formatMessage(tc, "SECURITY_TOKEN_TYPE_NOT_SUPPORTED_MSE1002"));
        }
        
        AuthenticationData authData = MessagingSecurityUtility
                        .createAuthenticationData(securityToken);
        Subject result = AccessController.doPrivileged(new MessagingLoginAction(
                        authData, MessagingSecurityConstants.LTPA, _messagingSecurityService.getSecurityService()));

        if (Audit.isAuditRequired(Audit.EventID.SECURITY_JMS_AUTHN_01, result == null ? AuditConstants.FAILURE : AuditConstants.SUCCESS)) {
            String busName = AuditManager.getJMSBusName();
            String messagingEngine = AuditManager.getJMSMessagingEngine();
            String credType = "Token";
            ConversationMetaData cmd = (ConversationMetaData) AuditManager.getJMSConversationMetaData();
            if (cmd != null) {
                Audit.audit(Audit.EventID.SECURITY_JMS_AUTHN_01, result == null ? null : new String(securityToken), cmd.getRemoteAddress().getHostAddress(), 
                            Integer.toString(cmd.getRemotePort()), cmd.getChainName(), busName, messagingEngine, credType,
                            Integer.valueOf(result == null ? 201 : 200));
            } else {
                Audit.audit(Audit.EventID.SECURITY_JMS_AUTHN_01, result == null ? null : new String(securityToken), null, null, null, busName, messagingEngine, credType, 
                            Integer.valueOf(result == null ? 201 : 200));
            }
        }

        if (TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled()) {
            SibTr.exit(tc, CLASS_NAME + "login", result);
        }

        if (result == null) {
            //114580
            throwAuthenticationException(null);//114580
        }

        return result;
    }

    @Override
    public Subject login(String userName) throws MessagingAuthenticationException {
        if (TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled()) {
            SibTr.entry(tc, CLASS_NAME + "login", userName);
        }
        AuthenticationData authData = MessagingSecurityUtility
                        .createAuthenticationData(userName, _messagingSecurityService.getUserRegistry());
        Subject result = AccessController
                        .doPrivileged(new MessagingLoginAction(authData,
                                        MessagingSecurityConstants.IDASSERTION, _messagingSecurityService.getSecurityService()));

        if (Audit.isAuditRequired(Audit.EventID.SECURITY_JMS_AUTHN_01, result == null ? AuditConstants.FAILURE : AuditConstants.SUCCESS)) {
            String busName = AuditManager.getJMSBusName();
            String messagingEngine = AuditManager.getJMSMessagingEngine();
            String credType = "UserId";
            //114580
            ConversationMetaData cmd = (ConversationMetaData) AuditManager.getJMSConversationMetaData();
            if (cmd != null) {
                Audit.audit(Audit.EventID.SECURITY_JMS_AUTHN_01, userName, cmd.getRemoteAddress().getHostAddress(), Integer.toString(cmd.getRemotePort()),
                            cmd.getChainName(), busName, messagingEngine, credType, Integer.valueOf(result == null ? 201 : 200));
            } else {
                Audit.audit(Audit.EventID.SECURITY_JMS_AUTHN_01, userName, null, null, null, busName, messagingEngine, credType, Integer.valueOf(result == null ? 201 : 200));
            }
        }

        if (TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled()) {
            SibTr.exit(tc, CLASS_NAME + "login", result);
        }

        if (result == null) {
            throwAuthenticationException(userName);//114580
        }

        return result;
    }

    @Override
    public Subject login(Certificate[] certificates) throws MessagingAuthenticationException {
        if (TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled()) {
            SibTr.entry(tc, CLASS_NAME + "login", certificates);
        }
        AuthenticationData authData = MessagingSecurityUtility
                        .createAuthenticationData(certificates, _messagingSecurityService.getUserRegistry());
        Subject result = AccessController.doPrivileged(new MessagingLoginAction(
                        authData, MessagingSecurityConstants.CLIENTSSL, _messagingSecurityService.getSecurityService()));

        if (Audit.isAuditRequired(Audit.EventID.SECURITY_JMS_AUTHN_01, result == null ? AuditConstants.FAILURE : AuditConstants.SUCCESS)) {
            String busName = AuditManager.getJMSBusName();
            String messagingEngine = AuditManager.getJMSMessagingEngine();
            String credType = "Certificates";
            ConversationMetaData cmd = (ConversationMetaData) AuditManager.getJMSConversationMetaData();
            if (cmd != null) {
                Audit.audit(Audit.EventID.SECURITY_JMS_AUTHN_01, result == null ? null : certificates.toString(), cmd.getRemoteAddress().getHostAddress(), 
                            Integer.toString(cmd.getRemotePort()), cmd.getChainName(), busName, messagingEngine, credType,
                            Integer.valueOf(result == null ? 201 : 200));
            } else {
                Audit.audit(Audit.EventID.SECURITY_JMS_AUTHN_01, result == null ? null : certificates.toString(), null, null, null, busName, messagingEngine, credType, 
                            Integer.valueOf(result == null ? 201 : 200));
            }
        }

        if (TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled()) {
            SibTr.exit(tc, CLASS_NAME + "login", result);
        }

        if (result == null) {
            throwAuthenticationException(null);//114580
        }

        return result;
    }

    @Override
    public void logout(Subject subj) {
        if (Audit.isAuditRequired(Audit.EventID.SECURITY_JMS_AUTHN_TERMINATE_01, AuditConstants.SUCCESS)) {
            String userName = null;
            try {
                userName = _messagingSecurityService.getUniqueUserName(subj);
            } catch (MessagingSecurityException e) {
                //No FFDC Code Needed
            }

            ConversationMetaData cmd = (ConversationMetaData) AuditManager.getJMSConversationMetaData();
            if (cmd != null) {
                Audit.audit(Audit.EventID.SECURITY_JMS_AUTHN_TERMINATE_01, userName, 
                            cmd.getRemoteAddress().getHostAddress(), Integer.toString(cmd.getRemotePort()),
                            cmd.getChainName(), AuditManager.getJMSBusName(), AuditManager.getJMSMessagingEngine(), null, Integer.valueOf(200));
            } else {
                Audit.audit(Audit.EventID.SECURITY_JMS_AUTHN_TERMINATE_01, userName, null, null, null, 
                            AuditManager.getJMSBusName(), AuditManager.getJMSMessagingEngine(), null, Integer.valueOf(200));
            }
        }

        /*
         * What should we do when we logout? In tWAS it is just executing some
         * Auditing features which are not supported in Liberty
         */
        if (TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled()) {
            SibTr.entry(tc, CLASS_NAME + "logout", subj);
        }
        if (TraceComponent.isAnyTracingEnabled() && tc.isEntryEnabled()) {
            SibTr.exit(tc, CLASS_NAME + "logout");
        }
    }

    private void throwAuthenticationException(String userName) throws MessagingAuthenticationException {
        throw new MessagingAuthenticationException(Tr.formatMessage(tc, "USER_NOT_AUTHENTICATED_MSE1009", userName));//114580
    }

}
