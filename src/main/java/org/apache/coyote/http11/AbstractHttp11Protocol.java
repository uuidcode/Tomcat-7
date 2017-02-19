/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.coyote.http11;


import org.apache.coyote.AbstractProtocol;
import org.apache.tomcat.util.res.StringManager;

public abstract class AbstractHttp11Protocol extends AbstractProtocol {

	/**
	 * The string manager for this package.
	 */
	protected static final StringManager sm =
			StringManager.getManager(Constants.Package);
	private int socketBuffer = 9000;


	// ------------------------------------------------ HTTP specific properties
	// ------------------------------------------ managed in the ProtocolHandler
	/**
	 * Maximum size of the post which will be saved when processing certain
	 * requests, such as a POST.
	 */
	private int maxSavePostSize = 4 * 1024;
	/**
	 * Maximum size of the HTTP message header.
	 */
	private int maxHttpHeaderSize = 8 * 1024;
	/**
	 * Specifies a different (usually  longer) connection timeout during data
	 * upload.
	 */
	private int connectionUploadTimeout = 300000;
	/**
	 * If true, the connectionUploadTimeout will be ignored and the regular
	 * socket timeout will be used for the full duration of the connection.
	 */
	private boolean disableUploadTimeout = true;
	/**
	 * Integrated compression support.
	 */
	private String compression = "off";
	private String noCompressionUserAgents = null;
	private String compressableMimeTypes = "text/html,text/xml,text/plain";
	private int compressionMinSize = 2048;
	/**
	 * Regular expression that defines the User agents which should be
	 * restricted to HTTP/1.0 support.
	 */
	private String restrictedUserAgents = null;
	/**
	 * Server header.
	 */
	private String server;
	/**
	 * Maximum size of trailing headers in bytes
	 */
	private int maxTrailerSize = 8192;
	/**
	 * Maximum size of extension information in chunked encoding
	 */
	private int maxExtensionSize = 8192;
	/**
	 * This field indicates if the protocol is treated as if it is secure. This
	 * normally means https is being used but can be used to fake https e.g
	 * behind a reverse proxy.
	 */
	private boolean secure;

	@Override
	protected String getProtocolName() {
		return "Http";
	}

	public int getSocketBuffer() {
		return socketBuffer;
	}

	public void setSocketBuffer(int socketBuffer) {
		this.socketBuffer = socketBuffer;
	}

	public int getMaxSavePostSize() {
		return maxSavePostSize;
	}

	public void setMaxSavePostSize(int valueI) {
		maxSavePostSize = valueI;
	}

	public int getMaxHttpHeaderSize() {
		return maxHttpHeaderSize;
	}

	public void setMaxHttpHeaderSize(int valueI) {
		maxHttpHeaderSize = valueI;
	}

	public int getConnectionUploadTimeout() {
		return connectionUploadTimeout;
	}

	public void setConnectionUploadTimeout(int i) {
		connectionUploadTimeout = i;
	}

	public boolean getDisableUploadTimeout() {
		return disableUploadTimeout;
	}

	public void setDisableUploadTimeout(boolean isDisabled) {
		disableUploadTimeout = isDisabled;
	}

	public String getCompression() {
		return compression;
	}

	public void setCompression(String valueS) {
		compression = valueS;
	}

	public String getNoCompressionUserAgents() {
		return noCompressionUserAgents;
	}

	public void setNoCompressionUserAgents(String valueS) {
		noCompressionUserAgents = valueS;
	}

	public String getCompressableMimeType() {
		return compressableMimeTypes;
	}

	public void setCompressableMimeType(String valueS) {
		compressableMimeTypes = valueS;
	}

	public String getCompressableMimeTypes() {
		return getCompressableMimeType();
	}

	public void setCompressableMimeTypes(String valueS) {
		setCompressableMimeType(valueS);
	}

	public int getCompressionMinSize() {
		return compressionMinSize;
	}

	public void setCompressionMinSize(int valueI) {
		compressionMinSize = valueI;
	}

	public String getRestrictedUserAgents() {
		return restrictedUserAgents;
	}

	public void setRestrictedUserAgents(String valueS) {
		restrictedUserAgents = valueS;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getMaxTrailerSize() {
		return maxTrailerSize;
	}

	public void setMaxTrailerSize(int maxTrailerSize) {
		this.maxTrailerSize = maxTrailerSize;
	}

	public int getMaxExtensionSize() {
		return maxExtensionSize;
	}

	public void setMaxExtensionSize(int maxExtensionSize) {
		this.maxExtensionSize = maxExtensionSize;
	}

	public boolean getSecure() {
		return secure;
	}

	public void setSecure(boolean b) {
		secure = b;
	}


	// ------------------------------------------------ HTTP specific properties
	// ------------------------------------------ passed through to the EndPoint

	public boolean isSSLEnabled() {
		return endpoint.isSSLEnabled();
	}

	public void setSSLEnabled(boolean SSLEnabled) {
		endpoint.setSSLEnabled(SSLEnabled);
	}


	/**
	 * Maximum number of requests which can be performed over a keepalive
	 * connection. The default is the same as for Apache HTTP Server.
	 */
	public int getMaxKeepAliveRequests() {
		return endpoint.getMaxKeepAliveRequests();
	}

	public void setMaxKeepAliveRequests(int mkar) {
		endpoint.setMaxKeepAliveRequests(mkar);
	}
}
