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
 * Create Date : 2017-3-21
 */

package org.sniper.persistence.sqlmap.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.sniper.commons.util.ClassUtils;
import org.sniper.commons.util.FileUtils;
import org.sniper.commons.util.StringUtils;
import org.sniper.persistence.sqlmap.SqlMapOperations;
import org.sniper.persistence.sqlmap.SqlMapUtils;

/**
 * IBatis DAO支持类
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public abstract class IBatisDaoSupport<T> extends SqlMapClientDaoSupport
		implements SqlMapOperations<T> {
	
	/** 当前DAO所关联的实体类型 */
	private Class<T> targetType;
	
	/** 是否自动构建命名空间 */
	private boolean autoBuildNamespace = true;
	
	/** 命名空间 */
	protected String namespace;
	
	@Override
	public Class<T> getTargetType() {
		return targetType;
	}

	@Override
	public void setTargetType(Class<T> targetType) {
		this.targetType = targetType;
	}

	@Override
	public void setAutoBuildNamespace(boolean autoBuildNamespace) {
		this.autoBuildNamespace = autoBuildNamespace;
	}

	@Override
	public boolean isAutoBuildNamespace() {
		return autoBuildNamespace;
	}

	@Override
	public void setNamespace(String namespace) {
		this.namespace = StringUtils.safeString(namespace);
	}

	@Override
	public String getNamespace() {
		return namespace;
	}
	
	@Override
	protected void initDao() throws Exception {
		initTargetType();
		initNamespace();
	}
	
	@SuppressWarnings("unchecked")
	protected void initTargetType() {
		if (this.targetType == null)
			this.targetType = ((Class<T>) ClassUtils.getSuperClassGenricType(getClass()));
	}
	
	/**
	 * 初始化命名空间
	 * @author <a href="mailto:code727@gmail.com">杜斌</a>
	 */
	protected void initNamespace() {
		if (autoBuildNamespace && StringUtils.isBlank(namespace)) {
			Class<?> customizeInterface = SqlMapUtils.getCustomizeDaoInterface(getClass());
			if (customizeInterface != null)
				namespace = customizeInterface.getName() + FileUtils.EXTENSION_SEPERATOR;
			else if (targetType != Object.class)
				namespace = targetType.getName() + FileUtils.EXTENSION_SEPERATOR;
			else
				namespace = StringUtils.EMPTY;
		} else {
			if (StringUtils.isNotBlank(namespace) && !namespace.endsWith(FileUtils.EXTENSION_SEPERATOR))
				namespace += FileUtils.EXTENSION_SEPERATOR;
		}
	}

}
