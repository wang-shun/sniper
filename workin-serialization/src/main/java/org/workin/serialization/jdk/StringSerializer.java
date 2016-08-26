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
 * Create Date : 2016-8-24
 */

package org.workin.serialization.jdk;

import org.workin.commons.util.CodecUtils;
import org.workin.commons.util.ObjectUtils;
import org.workin.serialization.SerializationException;
import org.workin.serialization.Serializer;
import org.workin.support.codec.CodecSupport;

/**
 * @description 字符串序列化器实现类
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public class StringSerializer extends CodecSupport implements Serializer {

	@Override
	public <T> byte[] serialize(T t) throws SerializationException {
		return t != null ? CodecUtils.getBytes(ObjectUtils.toString(t), getEncoding()) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String deserialize(byte[] bytes) throws SerializationException {
		return bytes != null ? CodecUtils.bytesToString(bytes, getEncoding()) : null;
	}

}