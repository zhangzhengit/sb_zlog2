package com.vo.conf;

import java.util.List;

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
public class ZDBConf {

	private String name;

	private Boolean enable;

	private String level;

	private String outTypeEnum;

	private String pattern;

	private String url;

	private String username;

	private String password;

	private List<String> excludedClass;

	private List<String> excludedPackage;

}
