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
 * Create Date : 2017-5-24
 */

package org.sniper.beans.parameter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sniper.commons.enums.logic.BooleanEnum;
import org.sniper.commons.util.CollectionUtils;
import org.sniper.commons.util.MapUtils;

/**
 * Map参数实现类
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public class MapParameter<K, V> implements Parameter<K, V> {
	
	/** 参数映射集 */
	protected Map<K, V> parameters;
	
	public MapParameter() {
		this((Map<K, V>) null);
	}
	
	public MapParameter(Parameter<K, V> parameter) {
		this(parameter != null ? parameter.getParameters() : null);
	}
	
	public MapParameter(Map<K, V> parameters) {
		this.parameters = MapUtils.newLinkedHashMap(parameters);
	}
	
	@Override
	public void setParameters(Map<K, V> parameters) {
		parameters.putAll(parameters);
	}

	@Override
	public Map<K, V> getParameters() {
		return parameters;
	}

	@Override
	public void add(K name, V value) {
		parameters.put(name, value);
	}

	@Override
	public V getValue(K name) {
		return parameters.get(name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <V1> V1 getValue(K name, Class<V1> clazz) {
		return (V1) this.getValue(name);
	}
	
	@Override
	public Set<K> getNames() {
		return parameters.keySet();
	}

	@Override
	public List<V> getValues() {
		return CollectionUtils.newArrayList(this.parameters.values());
	}

	@Override
	public void remove(K name) {
		parameters.remove(name);
	}

	@Override
	public void clear() {
		parameters.clear();
	}

	@Override
	public int size() {
		return parameters.size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean isNotEmpty() {
		return !isEmpty();
	}
	
	@Override
	public String toString() {
		return parameters.toString();
	}

	@Override
	public String getString(K name) {
		return getString(name, null);
	}

	@Override
	public String getString(K name, String defaultValue) {
		V value = parameters.get(name);
		return value != null ? value.toString() : defaultValue;
	}

	@Override
	public Boolean getBoolean(K name) {
		return getBoolean(name, null);
	}

	@Override
	public Boolean getBoolean(K name, Boolean defaultValue) {
		V value = parameters.get(name);
		if (value != null) {
			String str = value.toString();
			return Boolean.valueOf(BooleanEnum.TRUE.name().equalsIgnoreCase(str) 
					|| BooleanEnum.Y.name().equalsIgnoreCase(str) || String.valueOf(BooleanEnum.TRUE.ordinal()).equals(str));
		}
			
		return defaultValue;		
	}

	@Override
	public boolean getBooleanValue(K name) {
		return getBooleanValue(name, false);
	}

	@Override
	public boolean getBooleanValue(K name, boolean defaultValue) {
		return getBoolean(name, defaultValue);
	}

	@Override
	public Byte getByte(K name) {
		return getByte(name, null);
	}

	@Override
	public Byte getByte(K name, Byte defaultValue) {
		V value = parameters.get(name);
		return value instanceof Number ? ((Number) value).byteValue() : defaultValue;
	}
	
	@Override
	public byte getByteValue(K name) {
		return getByteValue(name, (byte) 0);
	}

	@Override
	public byte getByteValue(K name, byte defaultValue) {
		return getByte(name, defaultValue);
	}

	@Override
	public Short getShort(K name) {
		return getShort(name, null);
	}

	@Override
	public Short getShort(K name, Short defaultValue) {
		V value = parameters.get(name);
		return value instanceof Number ? ((Number) value).shortValue() : defaultValue;
	}
	
	@Override
	public short getShortValue(K name) {
		return getShortValue(name, (short) 0);
	}

	@Override
	public short getShortValue(K name, short defaultValue) {
		return getShort(name, defaultValue);
	}

	@Override
	public Integer getInteger(K name) {
		return getInteger(name, null);
	}

	@Override
	public Integer getInteger(K name, Integer defaultValue) {
		V value = parameters.get(name);
		return value instanceof Number ? ((Number) value).intValue() : defaultValue;
	}

	@Override
	public int getIntegerValue(K name) {
		return getIntegerValue(name, 0);
	}

	@Override
	public int getIntegerValue(K name, int defaultValue) {
		return getInteger(name, defaultValue);
	}

	@Override
	public Long getLong(K name) {
		return getLong(name, 0L);
	}

	@Override
	public Long getLong(K name, Long defaultValue) {
		V value = parameters.get(name);
		return value instanceof Number ? ((Number) value).longValue() : defaultValue;
	}

	@Override
	public long getLongValue(K name) {
		return getLongValue(name, 0L);
	}

	@Override
	public long getLongValue(K name, long defaultValue) {
		return getLong(name, defaultValue);
	}

	@Override
	public Float getFloat(K name) {
		return getFloat(name, null);
	}

	@Override
	public Float getFloat(K name, Float defaultValue) {
		V value = parameters.get(name);
		return value instanceof Number ? ((Number) value).floatValue() : defaultValue;
	}

	@Override
	public float getFloatValue(K name) {
		return getFloatValue(name, 0f);
	}

	@Override
	public float getFloatValue(K name, float defaultValue) {
		return getFloat(name, defaultValue);
	}

	@Override
	public Double getDouble(K name) {
		return getDouble(name, null);
	}

	@Override
	public Double getDouble(K name, Double defaultValue) {
		V value = parameters.get(name);
		return value instanceof Number ? ((Number) value).doubleValue() : defaultValue;
	}

	@Override
	public double getDoubleValue(K name) {
		return getDoubleValue(name, 0d);
	}

	@Override
	public double getDoubleValue(K name, double defaultValue) {
		return getDouble(name, defaultValue);
	}
		
}
