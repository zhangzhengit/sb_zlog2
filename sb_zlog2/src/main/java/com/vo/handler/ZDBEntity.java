package com.vo.handler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * @author zhangzhen
 * @date 2022年6月30日
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZDBEntity {

	private Long id;

	private String level;
	private String className;
	private String threadName;
	private String content;
	private Date createTime;
}
