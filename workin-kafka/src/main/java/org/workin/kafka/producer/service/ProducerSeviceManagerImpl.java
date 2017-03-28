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
 * Create Date : 2017-3-13
 */

package org.workin.kafka.producer.service;

import java.util.Map;

/**
 * 生产者服务管理实现类
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public class ProducerSeviceManagerImpl implements ProducerSeviceManager {
	
	private Map<String, ProducerSevice> producerSevices;

	@Override
	public void setProducerSevices(Map<String, ProducerSevice> producerSevices) {
		this.producerSevices = producerSevices;
	}

	@Override
	public Map<String, ProducerSevice> getProducerSevices() {
		return producerSevices;
	}

	@Override
	public ProducerSevice getProducerSevice(String key) {
		return producerSevices.get(key);
	}

}
