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
 * Create Date : 2015-11-17
 */

package org.sniper.commons.enums.status;

import org.sniper.commons.enums.AbstractLocaleEnums;

/**
 * 系统执行状态枚举类
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public final class ExecutionStatus extends AbstractLocaleEnums<Integer> {
	
	private ExecutionStatus(Integer key, String value) {
		super(key, value);
	}
	
	/** 成功 */
	public static final ExecutionStatus SUCCESS = new ExecutionStatus(1, "execution.status.success");
	
	/** 失败 */
	public static final ExecutionStatus FAILED = new ExecutionStatus(0, "execution.status.failed");
	
	/** 异常 */
	public static final ExecutionStatus EXCEPTION = new ExecutionStatus(-1, "execution.status.exception");
			
}
