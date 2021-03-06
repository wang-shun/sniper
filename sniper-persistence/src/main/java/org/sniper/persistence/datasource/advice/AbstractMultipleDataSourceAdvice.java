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
 * Create Date : 2015-5-13
 */

package org.sniper.persistence.datasource.advice;

import org.sniper.persistence.datasource.manager.MultipleDataSourceManager;
import org.sniper.spring.aop.AbstractMethodAroundAdvice;

/**
 * 多数据源切面抽象类
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public abstract class AbstractMultipleDataSourceAdvice extends AbstractMethodAroundAdvice {
	
	/** 多数据源管理器 */
	protected MultipleDataSourceManager multipleDataSourceManager;
	
	public void setMultipleDataSourceManager(MultipleDataSourceManager multipleDataSourceManager) {
		this.multipleDataSourceManager = multipleDataSourceManager;
	}
	
	@Override
	protected void checkProperties() {
		if (multipleDataSourceManager == null)
			throw new IllegalArgumentException("Property 'multipleDataSourceManager' is required");
	}

}
