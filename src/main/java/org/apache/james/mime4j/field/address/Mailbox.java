/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package org.apache.james.mime4j.field.address;

import java.util.Collections;
import java.util.List;

/**
 * Represents a single e-mail address.
 */
public class Mailbox extends Address {

    private static final long serialVersionUID = 1L;

    private static final DomainList EMPTY_ROUTE_LIST = new DomainList(
            Collections.<String> emptyList(), true);

    private final String name;
    private final DomainList route;
    private final String localPart;
    private final String domain;

    /**
     * Creates an unnamed mailbox without a route. Routes are obsolete.
     * 
     * @param localPart
     *            The part of the e-mail address to the left of the "@".
     * @param domain
     *            The part of the e-mail address to the right of the "@".
     */
    public Mailbox(String localPart, String domain) {
        this(null, null, localPart, domain);
    }

    /**
     * Creates an unnamed mailbox with a route. Routes are obsolete.
     * 
     * @param route
     *            The zero or more domains that make up the route. May be
     *            <code>null</code>.
     * @param localPart
     *            The part of the e-mail address to the left of the "@".
     * @param domain
     *            The part of the e-mail address to the right of the "@".
     */
    public Mailbox(DomainList route, String localPart, String domain) {
        this(null, route, localPart, domain);
    }

    /**
     * Creates a named mailbox without a route. Routes are obsolete.
     * 
     * @param name
     *            the name of the e-mail address. May be <code>null</code>.
     * @param localPart
     *            The part of the e-mail address to the left of the "@".
     * @param domain
     *            The part of the e-mail address to the right of the "@".
     */
    public Mailbox(String name, String localPart, String domain) {
        this(name, null, localPart, domain);
    }

    /**
     * Creates a named mailbox with a route. Routes are obsolete.
     * 
     * @param name
     *            the name of the e-mail address. May be <code>null</code>.
     * @param route
     *            The zero or more domains that make up the route. May be
     *            <code>null</code>.
     * @param localPart
     *            The part of the e-mail address to the left of the "@".
     * @param domain
     *            The part of the e-mail address to the right of the "@".
     */
    public Mailbox(String name, DomainList route, String localPart,
            String domain) {
        if (localPart == null || localPart.length() == 0)
            throw new IllegalArgumentException();

        this.name = name == null || name.length() == 0 ? null : name;
        this.route = route == null ? EMPTY_ROUTE_LIST : route;
        this.localPart = localPart;
        this.domain = domain == null || domain.length() == 0 ? null : domain;
    }

    /**
     * Creates a named mailbox based on an unnamed mailbox. Package private;
     * internally used by Builder.
     */
    Mailbox(String name, Mailbox baseMailbox) {
        this(name, baseMailbox.getRoute(), baseMailbox.getLocalPart(),
                baseMailbox.getDomain());
    }

    /**
     * Returns the name of the mailbox or <code>null</code> if it does not
     * have a name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the route list. If the mailbox does not have a route an empty
     * domain list is returned.
     */
    public DomainList getRoute() {
        return route;
    }

    /**
     * Returns the left part of the e-mail address (before "@").
     */
    public String getLocalPart() {
        return localPart;
    }

    /**
     * Returns the right part of the e-mail address (after "@").
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Returns the address in the form <i>localPart@domain</i>.
     * 
     * @return the address part of this mailbox.
     */
    public String getAddress() {
        if (domain == null) {
            return localPart;
        } else {
            return localPart + '@' + domain;
        }
    }

    @Override
    public String getDisplayString(boolean includeRoute) {
        includeRoute &= route != null;
        boolean includeAngleBrackets = name != null || includeRoute;

        StringBuilder sb = new StringBuilder();

        if (name != null) {
            sb.append(name);
            sb.append(' ');
        }

        if (includeAngleBrackets) {
            sb.append('<');
        }

        if (includeRoute) {
            sb.append(route.toRouteString());
            sb.append(':');
        }

        sb.append(localPart);

        if (domain != null) {
            sb.append('@');
            sb.append(domain);
        }

        if (includeAngleBrackets) {
            sb.append('>');
        }

        return sb.toString();
    }

    @Override
    protected final void doAddMailboxesTo(List<Mailbox> results) {
        results.add(this);
    }

}
