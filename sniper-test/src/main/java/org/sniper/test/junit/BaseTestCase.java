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
 * Create Date : 2015-1-13
 */

package org.sniper.test.junit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Assert;

/**
 * 基础单元测试类
 * @author <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public abstract class BaseTestCase extends Assert {

	protected final transient Logger logger;

	public BaseTestCase() {
		this.logger = LoggerFactory.getLogger(getClass());
	}
	
	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException localInterruptedException) {
		}
	}
}
