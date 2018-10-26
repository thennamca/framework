package com.techmango.kafka;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Thennarasu
 * SMI_177
 *
 */
public class MessageVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date createdOn = new Date(System.currentTimeMillis());

	private byte[] fileData;

	private String key = "key";

	private String message = "";

	private String appName = "common";

	// For PreviewNotify
	private String mediaFileName = "";

	private String recordType = "";

	private String size = "";

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getMediaFileName() {
		return mediaFileName;
	}

	public void setMediaFileName(String mediaFileName) {
		this.mediaFileName = mediaFileName;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

}
