/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.catalina.core;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.ParameterParser;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/**
 * Adaptor to allow {@link org.apache.tomcat.util.http.fileupload.FileItem} objects generated by the package renamed
 * commons-upload to be used by the Servlet 3.0 upload API that expects
 * {@link javax.servlet.http.Part}s.
 */
public class ApplicationPart implements Part {

	private final FileItem fileItem;
	private final File location;

	public ApplicationPart(FileItem fileItem, File location) {
		this.fileItem = fileItem;
		this.location = location;
	}

	@Override
	public void delete() throws IOException {
		fileItem.delete();
	}

	@Override
	public String getContentType() {
		return fileItem.getContentType();
	}

	@Override
	public String getHeader(String name) {
		if (fileItem instanceof DiskFileItem) {
			return ((DiskFileItem) fileItem).getHeaders().getHeader(name);
		}
		return null;
	}

	@Override
	public Collection<String> getHeaderNames() {
		if (fileItem instanceof DiskFileItem) {
			HashSet<String> headerNames = new HashSet<String>();
			Iterator<String> iter =
					((DiskFileItem) fileItem).getHeaders().getHeaderNames();
			while (iter.hasNext()) {
				headerNames.add(iter.next());
			}
			return headerNames;
		}
		return Collections.emptyList();
	}

	@Override
	public Collection<String> getHeaders(String name) {
		if (fileItem instanceof DiskFileItem) {
			HashSet<String> headers = new HashSet<String>();
			Iterator<String> iter =
					((DiskFileItem) fileItem).getHeaders().getHeaders(name);
			while (iter.hasNext()) {
				headers.add(iter.next());
			}
			return headers;
		}
		return Collections.emptyList();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return fileItem.getInputStream();
	}

	@Override
	public String getName() {
		return fileItem.getFieldName();
	}

	@Override
	public long getSize() {
		return fileItem.getSize();
	}

	@Override
	public void write(String fileName) throws IOException {
		File file = new File(fileName);
		if (!file.isAbsolute()) {
			file = new File(location, fileName);
		}
		try {
			fileItem.write(file);
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	public String getString(String encoding) throws UnsupportedEncodingException {
		return fileItem.getString(encoding);
	}

	/**
	 * Calls {@link #getSubmittedFileName()}.
	 *
	 * @deprecated Use {@link #getSubmittedFileName()} from Servlet 3.1 instead.
	 * This method will be removed in Tomcat 8.
	 */
	@Deprecated
	public String getFilename() {
		return getSubmittedFileName();
	}

	/**
	 * Adapted from FileUploadBase.getFileName(). Method name chosen to be
	 * consistent with Servlet 3.1.
	 */
	public String getSubmittedFileName() {
		String fileName = null;
		String cd = getHeader("Content-Disposition");
		if (cd != null) {
			String cdl = cd.toLowerCase(Locale.ENGLISH);
			if (cdl.startsWith("form-data") || cdl.startsWith("attachment")) {
				ParameterParser paramParser = new ParameterParser();
				paramParser.setLowerCaseNames(true);
				// Parameter parser can handle null input
				Map<String, String> params =
						paramParser.parse(cd, ';');
				if (params.containsKey("filename")) {
					fileName = params.get("filename");
					if (fileName != null) {
						fileName = fileName.trim();
					} else {
						// Even if there is no value, the parameter is present,
						// so we return an empty file name rather than no file
						// name.
						fileName = "";
					}
				}
			}
		}
		return fileName;
	}
}
