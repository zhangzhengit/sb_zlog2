package com.vo.handler;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * @author zhangzhen
 * @date 2022年12月15日
 *
 */
@Validated
public class AddDTO {

	private Integer appId;

	private String appName;

	private String k;

	public AddDTO(final Integer appId, final String appName, final String k) {
		this.appId = appId;
		this.appName = appName;
		this.k = k;
	}

	public AddDTO() {
		this.appId = null;
		this.appName = null;
		this.k = null;
	}

	public Integer getAppId() {
		return this.appId;
	}

	public String getAppName() {
		return this.appName;
	}

	public String getK() {
		return this.k;
	}

	public void setAppId(final Integer appId) {
		this.appId = appId;
	}

	public void setAppName(final String appName) {
		this.appName = appName;
	}

	public void setK(final String k) {
		this.k = k;
	}

}
