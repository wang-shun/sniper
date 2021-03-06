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
 * Create Date : 2017-8-15
 */

package org.sniper.http.headers.request;

import org.sniper.commons.enums.http.AcceptEncodingAlgorithm;
import org.sniper.commons.util.AssertUtils;
import org.sniper.http.headers.AbstractQualityFactor;

/**
 * 客户端能够理解的内容编码方式
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public class AcceptEncoding extends AbstractQualityFactor {
	
	private static final long serialVersionUID = 7600312994109431769L;
	
	/** 编码算法 */
	private AcceptEncodingAlgorithm algorithm;
	
	public AcceptEncoding() {
		this(AcceptEncodingAlgorithm.ANY);
	}
	
	public AcceptEncoding(AcceptEncodingAlgorithm algorithm) {
		this(algorithm, MAX_DEFAULT);
	}
	
	public AcceptEncoding(double qualityValue) {
		this(AcceptEncodingAlgorithm.ANY, qualityValue);
	}
	
	public AcceptEncoding(AcceptEncodingAlgorithm algorithm, double qualityValue) {
		setAlgorithm(algorithm);
		setQualityValue(qualityValue);
	}
	
	public AcceptEncodingAlgorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(AcceptEncodingAlgorithm algorithm) {
		AssertUtils.assertNotNull(algorithm, "Accept encoding algorithm must not be null");
		
		this.algorithm = algorithm;
	}

	@Override
	public String toString() {
		String qualityValue = super.toString();
		
		if (qualityValue.length() > 0) 
			return this.algorithm.getEncoding() + qualityValue;
		
		return this.algorithm.getEncoding();
	}
			
}
