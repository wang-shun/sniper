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
 * Create Date : 2015-11-16
 */

package org.sniper.web.mapper;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;

import org.sniper.beans.mapper.AbstractMapper;
import org.sniper.beans.mapper.MapToMapMapper;
import org.sniper.beans.mapper.Mapper;
import org.sniper.beans.mapper.MapperRule;
import org.sniper.web.WebUtils;

/**
 * javax.servlet.ServletRequest对象与Map对象之间的映射转换
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public class ServletRequestToMapMapper extends AbstractMapper<ServletRequest, Map<String, String>> {
	
	private Mapper<Map<String, String>, Map<String, String>> mapper;

	public ServletRequestToMapMapper() {
		this.mapper = new MapToMapMapper<String>();
	}
	
	@Override
	public void setMapperRules(Set<MapperRule> mapperRules) {
		this.mapper.setMapperRules(mapperRules);
	}
	
	@Override
	public Set<MapperRule> getMapperRules() {
		return this.mapper.getMapperRules();
	}
	
	@Override
	public void setAutoMapping(boolean autoMapping) {
		this.mapper.setAutoMapping(autoMapping);
	}
	
	@Override
	public boolean isAutoMapping() {
		return this.mapper.isAutoMapping();
	}

	@Override
	public Map<String, String> mapping(ServletRequest source, Set<MapperRule> mapperRules) throws Exception {
		return mapper.mapping(WebUtils.getParameters(source), mapperRules);
	}

}
