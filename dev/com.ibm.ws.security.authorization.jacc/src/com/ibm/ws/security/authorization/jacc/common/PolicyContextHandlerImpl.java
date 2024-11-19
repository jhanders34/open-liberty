/*******************************************************************************
 * Copyright (c) 2015, 2024 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.security.authorization.jacc.common;

import java.util.HashMap;

import javax.security.jacc.PolicyContextException;
import javax.security.jacc.PolicyContextHandler;

import com.ibm.ws.ffdc.annotation.FFDCIgnore;

public class PolicyContextHandlerImpl implements PolicyContextHandler {

    private static final String[] keysArray = getKeysArray();

    @FFDCIgnore(Throwable.class)
    private static String[] getKeysArray() {
        String[] keysArrayToUse;
        boolean principalMapperSupported = false;
        // If it is before EE 9, we can skip checking if is EE 11 or later version
        if (PolicyContextHandler.class.getName().startsWith("jakarta")) {
            Class<?> principalMapperClass = null;
            try {
                principalMapperClass = Class.forName("jakarta.security.jacc.PrincipalMapper", false, PolicyContextHandlerImpl.class.getClassLoader());
            } catch (Throwable t) {

            }
            principalMapperSupported = principalMapperClass != null;
        }
        if (principalMapperSupported) {
            /** Keys for Jakarta EE 11 and higher. */
            keysArrayToUse = new String[] {
                                            // Maintain order from EE8-. Probably doesn't matter.
                                            // except javax.security.auth since that is Java SE
                                            "javax.security.auth.Subject.container",
                                            "jakarta.xml.soap.SOAPMessage",
                                            "jakarta.servlet.http.HttpServletRequest",
                                            "jakarta.ejb.EnterpriseBean",
                                            "jakarta.ejb.arguments",

                                            // EE11+ unique keys below here.
                                            "jakarta.security.jacc.PrincipalMapper"
            };

        } else {
            // javax.ejb, javax.servlet and javax.xml.soap prefixed strings will be jakarta-ized during transformation
            keysArrayToUse = new String[] {
                                            // Maintain order from EE8-. Probably doesn't matter.
                                            // javax prefixed strings will be converted during transformation
                                            // except javax.security.auth since that is Java SE
                                            "javax.security.auth.Subject.container",
                                            "javax.xml.soap.SOAPMessage",
                                            "javax.servlet.http.HttpServletRequest",
                                            "javax.ejb.EnterpriseBean",
                                            "javax.ejb.arguments"
            };
        }
        return keysArrayToUse;
    }

    private static final PolicyContextHandlerImpl pchi = new PolicyContextHandlerImpl();

    private PolicyContextHandlerImpl() {
    }

    public static PolicyContextHandlerImpl getInstance() {
        return pchi;
    }

    @Override
    public boolean supports(String key) throws PolicyContextException {
        for (String value : keysArray) {
            if (key.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String[] getKeys() throws PolicyContextException {
        return keysArray.clone();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object getContext(String key, Object object) throws PolicyContextException {
        if (object == null) {
            return null;
        }
        return ((HashMap<String, Object>) object).get(key);
    }
}
