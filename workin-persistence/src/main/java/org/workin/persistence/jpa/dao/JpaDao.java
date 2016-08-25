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
 * Create Date : 2015-2-2
 */

package org.workin.persistence.jpa.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.workin.persistence.FilterQuery;
import org.workin.persistence.GenericDao;
import org.workin.persistence.jpa.dao.support.JpaCriteriaQuery;
import org.workin.persistence.jpa.dao.support.JpaNamedQuery;
import org.workin.persistence.jpa.dao.support.JpaNativePersistence;
import org.workin.persistence.jpa.dao.support.JpaNativeQuery;
import org.workin.persistence.jpa.dao.support.JpaPagingQuery;
import org.workin.persistence.jpa.dao.support.JpaPersistence;
import org.workin.persistence.jpa.dao.support.JpaQuery;

/**
 * @description JPA持久化数据访问接口
 * @author <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public interface JpaDao<T, PK extends Serializable> extends GenericDao<T>,
		JpaPersistence<T, PK>, JpaNativePersistence, JpaQuery<T, PK>, JpaNamedQuery<T>,
		JpaNativeQuery<T>, JpaCriteriaQuery<T>, JpaPagingQuery<T>, FilterQuery<T> {
	
	/**
	 * @description 获取EntityManager对象
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public EntityManager getEntityManager();
	
	/**
	 * @description 设置EntityManager对象
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager);

}
