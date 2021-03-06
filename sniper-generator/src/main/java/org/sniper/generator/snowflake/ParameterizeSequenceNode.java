/*
 * Copyright 2018 the original author or authors.
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
 * Create Date : 2018-6-29
 */

package org.sniper.generator.snowflake;

/**
 * 参数化序列节点
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public class ParameterizeSequenceNode extends SequenceNode {

	private static final long serialVersionUID = 5078851843348885113L;
	
	/** 是否将参数作为最终结果的一部分 */
	private boolean parameterAsResult;

	public ParameterizeSequenceNode() {
		super();
	}
	
	public ParameterizeSequenceNode(long workerId, long dataCenterId, boolean useTwepoch, boolean parameterAsResult) {
		super(workerId, dataCenterId, useTwepoch);
		this.parameterAsResult = parameterAsResult;
	}

	public boolean isParameterAsResult() {
		return parameterAsResult;
	}

	public void setParameterAsResult(boolean parameterAsResult) {
		this.parameterAsResult = parameterAsResult;
	}
	
}
