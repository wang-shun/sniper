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
 * Create Date : 2015-11-13
 */

package org.workin.support.parameter;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description 泛型参数接口
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public interface Parameter<K, V> {
	
	/**
	 * @description 新增参数
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param name
	 * @param value
	 */
	public void add(K name, V value);
	
	/**
	 * @description 获取指定名称的参数值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param name
	 * @return
	 */
	public V getValue(K name);
	
	/**
	 * @description 获取指定名称和类型的参数值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param name
	 * @param clazz
	 * @return
	 */
	public <V1> V1 getValue(K name, Class<V1> clazz);
	
	/**
	 * @description 获取所有的参数映射集
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public Map<K, V> getParameters();
	
	/**
	 * @description 获取所有的参数名
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public Set<K> getNames();
	
	/**
	 * @description 获取所有的参数值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public List<V> getValues();

}
