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
 * Create Date : 2017-9-11
 */

package org.sniper.http.headers.request;

import java.io.Serializable;

import org.sniper.commons.util.AssertUtils;

/**
 * HTTP文件范围值对象
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public class RangeValue implements Serializable {
	
	private static final long serialVersionUID = 7780203358324324802L;

	private long start;
	
	private Long end;
	
	public RangeValue() {
		this(0L);
	}
	
	public RangeValue(long start) {
		this(start, null);
	}
	
	public RangeValue(long start, Long end) {
		if (end != null) {
			AssertUtils.assertTrue(start != end, "Range start '" + start + "' must not equals end");
			if (start < end) {
				AssertUtils.assertTrue(start >= 0, "Range start '" + start + "' must be greater than or equals 0");
				AssertUtils.assertTrue(end >= 0, "Range end '" + end + "' must be greater than or equals 0");
				this.start = start;
				this.end = end;
			} else {
				AssertUtils.assertTrue(end >= 0, "Range start '" + end + "' must be greater than or equals 0");
				AssertUtils.assertTrue(start >= 0, "Range end '" + start + "' must be greater than or equals 0");
				this.start = end;
				this.end = start;
			}
		} else {
			AssertUtils.assertTrue(start >= 0, "Range start must be greater than or equals 0");
			this.start = start;
		}
	}
	
	@Override
	public String toString() {
		String result = start + "-";
		if (end != null)
			result += end;
		
		return result;
	}
	
}
