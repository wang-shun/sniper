/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Create Date : 2017年9月11日
 */

package org.sniper.commons.enums.http;

import java.util.Map;

import org.sniper.commons.util.MapUtils;

/**
 * 身份认证类型枚举
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public enum AuthenticationType {

	BASIC("Basic"),
	BEARER("Bearer"),
	DIGEST("Digest"),
	HOBA("HOBA"),
	MUTUAL("Mutual"),
	NEGOTIATE("Negotiate"),
	OAUTH("OAuth"),
	SCRAM_SHA_1("SCRAM-SHA-1"),
	SCRAM_SHA_256("SCRAM-SHA-256");
	
	private String name;
	
	private static final Map<String, AuthenticationType> mappings = MapUtils.newHashMap(9);
	
	static {
		for (AuthenticationType scheme : values()) {
			mappings.put(scheme.name, scheme);
		}
	}
	
	private AuthenticationType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	/**
	 * 将指定的名称解析成AuthenticationScheme对象
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param name
	 * @return
	 */
	public static AuthenticationType resolve(String name) {
		return (name != null ? mappings.get(name) : null);
	}

	/**
	 * 判断指定的名称是否匹配一个AuthenticationScheme对象
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param name
	 * @return
	 */
	public boolean matches(String name) {
		return this.name.equals(name);
	}
	
}
