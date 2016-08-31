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
 * Create Date : 2016-8-30
 */

package org.workin.nosql.mongodb.spring;

import java.io.Serializable;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.workin.nosql.mongodb.dao.MongoPersistence;

import com.mongodb.WriteResult;

/**
 * Spring MongoDB持久化接口
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public interface SpringMongoPersistence<T, PK extends Serializable> extends
		MongoPersistence<T, PK> {
	
	/**
	 * 更新查询结果集返回的第一条记录<p>
	 * 实际执行的语句为:db.collection.update({查询},{更新},false,false})
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param query 查询条件
	 * @param update 更新操作和数据
	 * @return
	 */
	public WriteResult updateFirst(Query query, Update update);
	
	/**
	 * 在目标集合中更新查询结果集返回的第一条记录<p>
	 * 实际执行的语句为:db.collection.update({查询},{更新},false,false})
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param query 查询条件
	 * @param update 更新操作和数据
	 * @param collection 目标集合
	 * @return
	 */
	public WriteResult updateFirst(Query query, Update update, String collection);
	
	/**
	 * 更新查询结果集返回的所有记录<p>
	 * 实际执行的语句为:db.collection.update({查询},{更新},false,true})
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param query 查询条件
	 * @param update 更新操作和数据
	 * @return
	 */
	public WriteResult updateMulti(Query query, Update update);
	
	/**
	 * 在目标集合中更新查询结果集返回的所有记录<p>
	 * 实际执行的语句为:db.collection.update({查询},{更新},false,true})
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param query 查询条件
	 * @param update 更新操作和数据
	 * @param collection 目标集合名称
	 * @return
	 */
	public WriteResult updateMulti(Query query, Update update, String collection);
	
	/**
	 * 更新满足查询条件或插入未满足查询条件的一条记录<p>
	 * 1.query如果查找到符合条件的结果集，则修改结果集中第一条记录<p>
	 * 2.query如果未找到符合条件的结果集，则连同update中的数据键值组合成新的一行记录插入到集合中<p>
	 * 实际执行的语句为:db.collection.update({查询},{更新},true,false})
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param query 查询条件
	 * @param update 更新操作和数据
	 * @return
	 */
	public WriteResult upsertOne(Query query, Update update);
	
	/**
	 * 在目标集合中更新满足查询条件或插入未满足查询条件的一条记录<p>
	 * 1.query如果查找到符合条件的结果集，则修改结果集中第一条记录<p>
	 * 2.query如果未找到符合条件的结果集，则连同update中的数据键值组合成新的一行记录插入到集合中<p>
	 * 实际执行的语句为:db.collection.update({查询},{更新},true,false})
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param query 查询条件
	 * @param update 更新操作和数据
	 * @param collection 目标集合名称
	 * @return
	 */
	public WriteResult upsertOne(Query query, Update update, String collection);
	
	/**
	 * 更新满足查询条件的多条或插入未满足查询条件的一条记录<p>
	 * 1.query如果查找到符合条件的结果集，则修改结果集中所有记录<p>
	 * 2.query如果未找到符合条件的结果集，则连同update中的数据键值组合成新的一行记录插入到集合中<p>
	 * 实际执行的语句为:db.collection.update({查询},{更新},true,true})
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param query
	 * @param update
	 * @return
	 */
	public WriteResult upsertMulti(Query query, Update update);
	
	/**
	 * 在目标集合中更新满足查询条件的多条或插入未满足查询条件的一条记录<p>
	 * 1.query如果查找到符合条件的结果集，则修改结果集中所有记录<p>
	 * 2.query如果未找到符合条件的结果集，则连同update中的数据键值组合成新的一行记录插入到集合中<p>
	 * 实际执行的语句为:db.collection.update({查询},{更新},true,true})
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param query 查询条件
	 * @param update 更新操作和数据
	 * @param collection 目标集合名称
	 * @return
	 */
	public WriteResult upsertMulti(Query query, Update update, String collection);
	
	/**
	 * 更新查询结果集返回的第一条记录，并返回更新前的数据对象<p>
	 * 实际执行的语句为:db.collection.findAndModify({"query":{查询},"update":{更新}})
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param query 查询条件
	 * @param update 更新操作和数据
	 * @return
	 */
	public T findAndModify(Query query, Update update);
	
	/**
	 * 在目标集合中更新查询结果集返回的第一条记录，并返回更新前的数据对象<p>
	 * 实际执行的语句为:db.collection.findAndModify({"query":{查询},"update":{更新}})
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param query 查询条件
	 * @param update 更新操作和数据
	 * @param collection 目标集合名称
	 * @return
	 */
	public T findAndModify(Query query, Update update, String collection);
	
	/**
	 * 删除查询结果集返回的第一条记录，并返回删除前的数据对象<p>
	 * 实际执行的语句为:db.collection.findAndModify({"query":{查询},"remove":true})
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param query 查询条件
	 * @return
	 */
	public T findAndRemove(Query query);
	
	/**
	 * 在目标集合中删除查询结果集返回的第一条记录，并返回删除前的数据对象<p>
	 * 实际执行的语句为:db.collection.findAndModify({"query":{查询},"remove":true})
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param query 查询条件
	 * @param collection 目标集合名称
	 * @return
	 */
	public T findAndRemove(Query query, String collection);

}