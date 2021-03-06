/*
 * Copyright 2018 the original author or authors.
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
 * Create Date : 2018-6-29
 */

package org.sniper.generator.snowflake;

import java.math.BigInteger;

import org.sniper.commons.util.AssertUtils;
import org.sniper.commons.util.DateUtils;
import org.sniper.commons.util.NumberUtils;
import org.sniper.generator.Generator;

/**
 * 推特Snowflake算法生成器抽象类
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public abstract class AbstractSnowflakeGenerator<T> implements Generator<T> {
	
	/** 相对的开始时间截 */
    protected final long twepoch = DateUtils.stringToMillis("2017-01-01 00:00:00");

    /** 机器id所占的位数 */
    protected final long workerIdBits = 5L;

    /** 数据标识id所占的位数 */
    protected final long dataCenterIdBits = 5L;

    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    protected final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /** 支持的最大数据标识id，结果是31 */
    protected final long maxDatacenterId = -1L ^ (-1L << dataCenterIdBits);

    /** 序列在id中占的位数 */
    protected final long sequenceBits = 12L;

    /** 机器ID向左移12位 */
    protected final long workerIdShift = sequenceBits;

    /** 数据标识id向左移17位(12+5) */
    protected final long datacenterIdShift = sequenceBits + workerIdBits;

    /** 时间截向左移22位(5+5+12) */
    protected final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    protected final long sequenceMask = -1L ^ (-1L << sequenceBits);
    
    /** 服务器ID */
    protected final long workerId;

    /** 数据中心ID*/
    protected final long dataCenterId;
            
    protected AbstractSnowflakeGenerator(SequenceNode sequenceNode) {
    	AssertUtils.assertNotNull(sequenceNode, "Sequence node must not be null");
    	
    	long workerId = sequenceNode.getWorkerId();
    	long dataCenterId = sequenceNode.getDataCenterId();
    	
    	AssertUtils.assertTrue(workerId >= 0 && workerId <= maxWorkerId,
				String.format("Parameter 'workerId' must within interval [0-%d]", maxWorkerId));
		
		AssertUtils.assertTrue(dataCenterId >= 0 && dataCenterId <= maxDatacenterId,
				String.format("Parameter 'dataCenterId' must within interval [0-%d]", maxDatacenterId));
    	
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }
    
    /**
	 * 生成下一步毫秒时间刻度
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param lastTimestamp
	 * @return
	 */
	protected long nextMillis(long lastTimestamp) {
        long timestamp = currentTimestamp();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTimestamp();
        }
        return timestamp;
    }

	/**
	 * 获取当前时间戳
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
    protected long currentTimestamp() {
        return System.currentTimeMillis();
    }
    
    /**
	 * 时间序列类
	 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
	 * @version 1.0
	 */
	class TimeSequence {

		/** 毫秒内序列(0~4095) */
		private long sequence;

		/** 最近生成的时间截 */
		private long lastTimestamp = -1L;

		public long getSequence() {
			return sequence;
		}

		/**
		 * 生成时间戳内的下一序列
		 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
		 * @return
		 */
		public long nextSequence() {
			return sequence = (sequence + 1) & sequenceMask;
		}

		/**
		 * 重置时间戳内的序列计数
		 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
		 * @return
		 */
		public long resetSequence() {
			return sequence = 0;
		}

		public long getLastTimestamp() {
			return lastTimestamp;
		}

		public void setLastTimestamp(long lastTimestamp) {
			this.lastTimestamp = lastTimestamp;
		}
	}
	
	/**
	 * 序列生成器接口
	 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
	 * @version 1.0
	 */
	interface SequenceGenerator<T> {

		/** 
		 * 根据指定的时间戳和序列对象生成序列结果
		 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
		 * @param timestamp
		 * @param timeSequence
		 * @return 
		 */
		public T generate(long timestamp, AbstractSnowflakeGenerator<T>.TimeSequence timeSequence);
	}
	
	/**
	 * 有相对开始时间截参与的序列生成器实现类
	 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
	 * @version 1.0
	 */
	class TwepochSequenceGenerator implements SequenceGenerator<Number> {

		/**
		 * 生成通过移位并进行"或"运算后拼到一起组成的64位序列结果
		 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
		 * @param timestamp
		 * @param timeSequence
		 * @return 
		 */
		@Override
		public Number generate(long timestamp, AbstractSnowflakeGenerator<Number>.TimeSequence timeSequence) {
			return ((timestamp - twepoch) << timestampLeftShift) | (dataCenterId << datacenterIdShift)
					| (workerId << workerIdShift) | timeSequence.getSequence();
		}
	}
	
	/**
	 * 无相对开始时间截参与的序列生成器实现类
	 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
	 * @version 1.0
	 */
	class UntwepochSequenceGenerator implements SequenceGenerator<Number> {

		/**
		 * 生成由"当前毫秒时间戳(13位)+3位当前毫秒时间戳内的序号+2位服务器ID+2位数据中心ID"拼接而成的20位结果，
		 * 由于已经超出Long类型的范围，因此这里返回的结果类型为BigInteger
		 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
		 * @param timestamp
		 * @param timeSequence
		 * @return 
		 */
		@Override
		public Number generate(long timestamp, AbstractSnowflakeGenerator<Number>.TimeSequence timeSequence) {
			String value = new StringBuilder(20).append(timestamp).append(NumberUtils.format(timeSequence.getSequence(), 3))
					.append(NumberUtils.format(workerId, 2)).append(NumberUtils.format(dataCenterId, 2)).toString();
					
			return new BigInteger(value);
		}
	}
	
}
