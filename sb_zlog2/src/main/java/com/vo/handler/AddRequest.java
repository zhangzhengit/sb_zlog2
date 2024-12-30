package com.vo.handler;

import java.util.List;

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
public class AddRequest {

	private final List<byte[]> list;

	public AddRequest(final List<byte[]> list) {
		this.list = list;
	}


}
