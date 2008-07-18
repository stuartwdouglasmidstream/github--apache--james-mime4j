/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.james.mime4j;

/**
 * Describes <a href='http://tools.ietf.org/html/rfc1864'>RFC1864</a>  
 * <code>Content-MD5</code>.
 */
public interface RFC1864ContentMD5Descriptor {

    /**
     * Gets the raw, Base64 encoded value of the
     * <code>Content-MD5</code> field.
     * See <a href='http://tools.ietf.org/html/rfc1864'>RFC1864</a>.
     * @return raw encoded content-md5
     * or null if no header exists
     */
    public abstract String getContentMD5Raw();

}