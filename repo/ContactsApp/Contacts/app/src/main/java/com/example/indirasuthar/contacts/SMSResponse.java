package com.example.indirasuthar.contacts;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SMSResponse implements Serializable {

	@SerializedName("message")
	public List<String> message;

	@SerializedName("request_id")
	private String requestId;

	@SerializedName("return")
	private boolean jsonMemberReturn;

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public boolean isJsonMemberReturn() {
		return jsonMemberReturn;
	}

	public void setJsonMemberReturn(boolean jsonMemberReturn) {
		this.jsonMemberReturn = jsonMemberReturn;
	}


	@Override
	public String toString() {
		return "SMSResponse{" +
				"message=" + message +
				", requestId='" + requestId + '\'' +
				", jsonMemberReturn=" + jsonMemberReturn +
				'}';
	}
}