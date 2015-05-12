/*
 * Copyright 2015 the original author or authors.
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
 * Create Date : 2015-5-12
 */

package org.workin.persistence.datasource;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.PatternMatchUtils;
import org.workin.commons.util.MapUtils;

/**
 * @description 多数据源管理抽象类，主要维护方法与所选数据源之间的关系
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public abstract class AbstractMultipleDataSourceManager implements InitializingBean {
	
	/** 保存方法名称模式与相关数据源名称之间的关系 */
	private Map<String, String> methodPatternAndDataSourceName;
	
	private Set<String> methodPattern;

	public void setMethodPatternAndDataSourceName(
			Map<String, String> methodPatternAndDataSourceName) {
		this.methodPatternAndDataSourceName = methodPatternAndDataSourceName;
	}
	
	public void afterPropertiesSet() throws Exception {
		if (MapUtils.isEmpty(methodPatternAndDataSourceName))
			throw new IllegalArgumentException("Property 'methodPatternAndDataSourceName' is required");
		
		this.methodPattern = methodPatternAndDataSourceName.keySet();
	}
	
	/**
	 * @description 根据方法名称获取匹配模式对应的数据源名称
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param methodPattern
	 * @return
	 */
	protected String getDataSourceName(String methodMame) {
		for (String pattern : this.methodPattern) {
			if (PatternMatchUtils.simpleMatch(pattern, methodMame)) 
				return this.methodPatternAndDataSourceName.get(pattern);
		}
		
		return null;
	}

}
