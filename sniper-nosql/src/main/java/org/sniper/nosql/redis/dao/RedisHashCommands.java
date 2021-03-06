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
 * Create Date : 2015-3-28
 */

package org.sniper.nosql.redis.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis哈希命令接口
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public interface RedisHashCommands {
	
	/**
	 * 在当前库中执行hSet命令
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param value 值
	 * @return
	 */
	public <K, H, V> Boolean hSet(K key, H hashKey, V value);
	
	/**
	 * 在当前库中执行hSet命令，并设置过期秒数
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param value 值
	 * @param expireSeconds
	 * @return
	 */
	public <K, H, V> Boolean hSet(K key, H hashKey, V value, long expireSeconds);
	
	/**
	 * 在指定库中执行hSet命令
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param value 值
	 * @return
	 */
	public <K, H, V> Boolean hSet2(String dbName, K key, H hashKey, V value);
	
	/**
	 * 在当前库中执行hSet命令，并设置过期秒数
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param value 值
	 * @param expireSeconds
	 * @return
	 */
	public <K, H, V> Boolean hSet2(String dbName, K key, H hashKey, V value, long expireSeconds);
	
	/**
	 * 在当前库中执行hSetNX命令
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param value 值
	 * @return
	 */
	public <K, H, V> Boolean hSetNX(K key, H hashKey, V value);
	
	/**
	 * 在当前库中执行hSetNX命令，并设置过期秒数
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key
	 * @param hashKey 哈希键
	 * @param value
	 * @param expireSeconds
	 * @return
	 */
	public <K, H, V> Boolean hSetNX(K key, H hashKey, V value, long expireSeconds);
	
	/**
	 * 在指定库中执行hSetNX命令
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param value 值
	 * @return
	 */
	public <K, H, V> Boolean hSetNX2(String dbName, K key, H hashKey, V value);
	
	/**
	 * 在指定库中执行hSetNX命令，并设置过期秒数
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param value 值
	 * @param expireSeconds
	 * @return
	 */
	public <K, H, V> Boolean hSetNX2(String dbName, K key, H hashKey, V value, long expireSeconds);
	
	/**
	 * 在当前库中执行hMSet命令
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKeyValues 哈希键值映射集
	 */
	public <K, H, V> void hMSet(K key, Map<H, V> hashKeyValues);
	
	/**
	 * 在当前库中执行hMSet命令，并设置过期秒数
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKeyValues 哈希键值映射集
	 * @param expireSeconds
	 */
	public <K, H, V> void hMSet(K key, Map<H, V> hashKeyValues, long expireSeconds);
	
	/**
	 * 在指定库中执行hMSet命令
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKeyValues 哈希键值映射集
	 */
	public <K, H, V> void hMSet(String dbName, K key, Map<H, V> hashKeyValues);
	
	/**
	 * 在当前库中执行hMSet命令，并设置过期秒数
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKeyValues 哈希键值映射集
	 * @param expireSeconds
	 */
	public <K, H, V> void hMSet(String dbName, K key, Map<H, V> hashKeyValues, long expireSeconds);
	
	/**
	 * 删除当前库指定键对应的哈希键值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @return
	 */
	public <K, H> Long hDel(K key, H hashKey);
	
	/**
	 * 在指定库删除键对应的哈希键值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKey 哈希键
	 * @return
	 */
	public <K, H> Long hDel(String dbName, K key, H hashKey);
	
	/**
	 * 删除当前库指定键对应的多个哈希键值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKeys 哈希键数组
	 * @return
	 */
	public <K, H> Long hDel(K key, H[] hashKeys);
	
	/**
	 * 删除指定库键对应的多个哈希键值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKeys 哈希键数组
	 * @return
	 */
	public <K, H> Long hDel(String dbName, K key, H[] hashKeys);
	
	/**
	 * 删除当前库指定键对应的多个哈希键值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKeys 哈希键集合
	 * @return
	 */
	public <K, H> Long hDel(K key, Collection<H> hashKeys);
	
	/**
	 * 删除指定库键对应的多个域值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKeys 哈希键集合
	 * @return
	 */
	public <K, H> Long hDel(String dbName, K key, Collection<H> hashKeys);
	
	/**
	 * 判断当前库指定键对应的哈希键是否存在
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @return
	 */
	public <K, H> Boolean hExists(K key, H hashKey);
	
	/**
	 * 判断指定库键对应域是否存在
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKey 哈希键
	 * @return
	 */
	public <K, H> Boolean hExists(String dbName, K key, H hashKey);
	
	/**
	 * 在当前库中执行hGet命令，获取键中指定的哈希键值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @return
	 */
	public <K, H, V> V hGet(K key, H hashKey);
	
	/**
	 * 在当前库中执行hGet命令，获取哈希键中指定类型的哈希键值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param valueType
	 * @return
	 */
	public <K, H, V> V hGet(K key, H hashKey, Class<V> valueType);
	
	/**
	 * 在指定库中执行hGet命令，获取哈希键中指定的哈希键值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKey 哈希键
	 * @return
	 */
	public <K, H, V> V hGet2(String dbName, K key, H hashKey);
	
	/**
	 * 在指定库中执行hGet命令，获取哈希键中指定类型的哈希键值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKey 哈希键
	 * @param valueType 哈希值类型
	 * @return
	 */
	public <K, H, V> V hGet2(String dbName, K key, H hashKey, Class<V> valueType);
	
	/**
	 * 在当前库中执行hGetAll命令，获取键中所有的哈希键值映射集
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @return 
	 */
	public <K, H, V> Map<H, V> hGetAll(K key);
	
	/**
	 * 在当前库中执行hGetAll命令，获取键中所有指定类型的哈希键值映射集
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param valueType 哈希值类型
	 * @return
	 */
	public <K, H, V> Map<H, V> hGetAll(K key, Class<V> valueType);
	
	/**
	 * 在当前库中执行hGetAll命令，获取键中所有指定类型的哈希键值映射集
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKeyType 哈希键类型
	 * @param valueType 哈希值类型
	 * @return
	 */
	public <K, H, V> Map<H, V> hGetAll(K key, Class<H> hashKeyType, Class<V> valueType);
	
	/** 
	 * 在指定库中执行hGetAll命令，获取键中所有的哈希键值映射集
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @return
	 */
	public <K, H, V> Map<H, V> hGetAll2(String dbName, K key);
	
	/**
	 * 在指定库中执行hGetAll命令，获取键中所有指定类型的哈希键值映射集
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param valueType 哈希值类型
	 * @return
	 */
	public <K, H, V> Map<H, V> hGetAll2(String dbName, K key, Class<V> valueType);
	
	/**
	 * 在指定库中执行hGetAll命令，获取键中所有指定类型的哈希键值映射集
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKeyType 哈希键类型
	 * @param valueType 哈希值类型
	 * @return
	 */
	public <K, H, V> Map<H, V> hGetAll2(String dbName, K key, Class<H> hashKeyType, Class<V> valueType);
	
	/**
	 * 在当前库中执行hKeys命令，获取键对应的所有哈希键
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @return
	 */
	public <K, H> Set<H> hKeys(K key);
	
	/**
	 * 在当前库中执行hKeys命令，获取键对应的所有哈希键
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKeyType 哈希键类型
	 * @return
	 */
	public <K, H> Set<H> hKeys(K key, Class<H> hashKeyType);
	
	/**
	 * 在指定库中执行hKeys命令，获取键对应的所有哈希键
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @return
	 */
	public <K, H> Set<H> hKeys2(String dbName, K key);
	
	/**
	 * 在指定库中执行hKeys命令，获取键对应的所有哈希键
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKeyType 哈希键类型
	 * @return
	 */
	public <K, H> Set<H> hKeys2(String dbName, K key, Class<H> hashKeyType);
	
	/**
	 * 在当前库中执行hLen命令，获取键对应的域个数
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key
	 * @return
	 */
	public <K> Long hLen(K key);
	
	/**
	 * 在指定库中执行hLen命令，获取键对应的域个数
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key
	 * @return
	 */
	public <K> Long hLen(String dbName, K key);
	
	/**
	 * 在当前库中执行hMGet命令，获取键中多个哈希键的值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKeys 哈希键集合
	 * @return
	 */
	public <K, H, V> List<V> hMGet(K key, Collection<H> hashKeys);
	
	/**
	 * 在当前库中执行hMGet命令，获取键中多个哈希键的值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKeys 哈希键集合
	 * @param valueType 哈希值的类型
	 * @return
	 */
	public <K, H, V> List<V> hMGet(K key, Collection<H> hashKeys, Class<V> valueType);
	
	/**
	 * 在指定库中执行hMGet命令，获取键中多个哈希键的值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKeys 哈希键集合
	 * @return
	 */
	public <K, H, V> List<V> hMGet(String dbName, K key, Collection<H> hashKeys);
	
	/**
	 * 在指定库中执行hMGet命令，获取键中多个哈希键的值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKeys 哈希键集合
	 * @param valueType 哈希值类型
	 * @return
	 */
	public <K, H, V> List<V> hMGet(String dbName, K key, Collection<H> hashKeys, Class<V> valueType);
	
	/**
	 * 在当前库中执行hMGet命令，获取键中多个哈希键的值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKeys 哈希键数组
	 * @return
	 */
	public <K, H, V> List<V> hMGet(K key, H[] hashKeys);
	
	/**
	 * 在当前库中执行hMGet命令，获取键中多个哈希键的值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key 键
	 * @param hashKeys 哈希键数组
	 * @param valueType 哈希值类型
	 * @return
	 */
	public <K, H, V> List<V> hMGet(K key, H[] hashKeys, Class<V> valueType);
	
	/**
	 * 在指定库中执行hMGet命令，获取键中多个哈希键的值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKeys 哈希键数组
	 * @return
	 */
	public <K, H, V> List<V> hMGet(String dbName, K key, H[] hashKeys);
	
	/**
	 * 在指定库中执行hMGet命令，获取键中多个域的值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key 键
	 * @param hashKeys 哈希键数组
	 * @param valueType 哈希值类型
	 * @return
	 */
	public <K, H, V> List<V> hMGet(String dbName, K key, H[] hashKeys, Class<V> valueType);
	
	/**
	 * 在当前库中执行hVals命令，获取键中所有域的值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key
	 * @return
	 */
	public <K, V> List<V> hVals(K key);
	
	/**
	 * 在当前库中执行hVals命令，获取键中所有域的值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param key
	 * @param valueType
	 * @return
	 */
	public <K, V> List<V> hVals(K key, Class<V> valueType);
	
	/**
	 * 在指定库中执行hMGet命令，获取键中所有域的值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key
	 * @return
	 */
	public <K, V> List<V> hVals2(String dbName, K key);
	
	/**
	 * 在指定库中执行hMGet命令，获取键中所有域的值
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dbName
	 * @param key
	 * @param valueType
	 * @return
	 */
	public <K, V> List<V> hVals2(String dbName, K key, Class<V> valueType);

}
