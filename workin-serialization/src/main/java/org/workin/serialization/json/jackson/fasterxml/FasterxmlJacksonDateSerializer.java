/*
 * Copyright 2016 the original author or authors.
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
 * Create Date : 2016-7-13
 */

package org.workin.serialization.json.jackson.fasterxml;

import java.io.IOException;
import java.util.Date;


import org.workin.commons.util.DateUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * FasterxmlJackson 日期序列化器实现类
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public class FasterxmlJacksonDateSerializer extends JsonSerializer<Date> {
	
	private String dateFormat;
	
	public FasterxmlJacksonDateSerializer() {
		
	}
	
	public FasterxmlJacksonDateSerializer(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public void serialize(Date date, JsonGenerator jgen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		
		jgen.writeString(DateUtils.dateToString(date, dateFormat));  
	}
	
}