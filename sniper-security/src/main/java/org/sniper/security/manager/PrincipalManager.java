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
 * Create Date : 2015-1-27
 */

package org.sniper.security.manager;

/**
 * Principal管理器接口
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public interface PrincipalManager {
	
	/**
	 * 获取当前用户登录名
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public String getCurrentLoginName();
		
	/**
	 * 获取当前用户姓名
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public String getCurrentUserName();
	
	/**
	 * 获取当前用户信息
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public <T> T getCurrentUser();

}
