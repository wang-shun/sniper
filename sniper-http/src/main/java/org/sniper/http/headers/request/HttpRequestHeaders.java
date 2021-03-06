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
 * Create Date : 2015-7-5
 */

package org.sniper.http.headers.request;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParsePosition;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.sniper.beans.parameter.DefaultParameters;
import org.sniper.beans.parameter.Parameters;
import org.sniper.commons.KeyValuePair;
import org.sniper.commons.enums.http.AcceptEncodingAlgorithm;
import org.sniper.commons.enums.http.AuthenticationType;
import org.sniper.commons.enums.http.HttpMethod;
import org.sniper.commons.enums.http.HttpProtocol;
import org.sniper.commons.enums.http.TEAlgorithm;
import org.sniper.commons.enums.http.headers.HttpRequestHeadersEnum;
import org.sniper.commons.util.ArrayUtils;
import org.sniper.commons.util.CollectionUtils;
import org.sniper.commons.util.DateUtils;
import org.sniper.commons.util.MapUtils;
import org.sniper.commons.util.NumberUtils;
import org.sniper.commons.util.StringUtils;
import org.sniper.http.headers.Forwarded;
import org.sniper.http.headers.HttpHeaders;
import org.sniper.http.headers.MediaType;

/**
 * HTTP请求头
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public class HttpRequestHeaders extends HttpHeaders {
		
	private static final long serialVersionUID = 850698081249888505L;
	
	/**
	 * 设置Accept
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param mediaTypes
	 */
	public void setAccept(List<MediaType> mediaTypes) {
		set(HttpRequestHeadersEnum.ACCEPT.getKey(), CollectionUtils.join(mediaTypes, VALUE_SEPARATOR));
	}
	
	/**
	 * 获取Accept
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public List<MediaType> getAccept() {
		String first = getFirst(HttpRequestHeadersEnum.ACCEPT.getKey());
		String[] values = StringUtils.split(first, VALUE_SEPARATOR);
		
		if (ArrayUtils.isNotEmpty(values)) {
			List<MediaType> list = CollectionUtils.newArrayList(values.length);
			MediaType mediaType;
			for (String value : values) {
				mediaType = MediaType.parse(value);
				if (mediaType != null)
					list.add(mediaType);
			}
			return list;
		}
		
		return null;
	}
			
	/**
	 * 设置客户端可以处理的字符集类型(Accept-Charset)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param charsets
	 */
	public void setAcceptCharset(List<AcceptCharset> charsets) {
		set(HttpRequestHeadersEnum.ACCEPT_CHARSET.getKey(), CollectionUtils.join(charsets, VALUE_SEPARATOR));
	}
	
	/**
	 * 获取客户端可以处理的字符集类型(Accept-Charset)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public List<AcceptCharset> getAcceptCharset() {
		String first = getFirst(HttpRequestHeadersEnum.ACCEPT_CHARSET.getKey());
		String[] values = StringUtils.split(first, VALUE_SEPARATOR);
		
		if (ArrayUtils.isNotEmpty(values)) {
			List<AcceptCharset> list = CollectionUtils.newArrayList(values.length);
			String charsetName;
			String weight;
			
			for (String value : values) {
				weight = StringUtils.afterLast(value, StringUtils.ASSIGNMENT);
				charsetName = StringUtils.beforeFrist(value, StringUtils.SEMICOLON);
				
				if (StringUtils.isBlank(charsetName))
					charsetName = value;
				
				if (StringUtils.isNotBlank(weight)) 
					list.add(new AcceptCharset(charsetName, Double.parseDouble(weight)));
				else
					list.add(new AcceptCharset(charsetName));
			}
			return list;
		}
		
		return null;
	}
	
	/**
	 * 设置客户端能够理解的内容编码方式(Accept-Encoding)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param acceptEncoding
	 */
	public void setAcceptEncoding(List<AcceptEncoding> encodings) {
		set(HttpRequestHeadersEnum.ACCEPT_ENCODING.getKey(), CollectionUtils.join(encodings, VALUE_SEPARATOR));
	}
	
	/**
	 * 获取设置客户端能够理解的内容编码方式(Accept-Encoding)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public List<AcceptEncoding> getAcceptEncoding() {
		String first = getFirst(HttpRequestHeadersEnum.ACCEPT_ENCODING.getKey());
		String[] values = StringUtils.split(first, VALUE_SEPARATOR);
		
		if (ArrayUtils.isNotEmpty(values)) {
			List<AcceptEncoding> list = CollectionUtils.newArrayList();
			String encoding;
			String weight;
			AcceptEncodingAlgorithm algorithm;
			
			for (String value : values) {
				
				weight = StringUtils.afterLast(value, StringUtils.ASSIGNMENT);
				encoding = StringUtils.beforeFrist(value, StringUtils.SEMICOLON);
				
				if (StringUtils.isNotEmpty(encoding))
					algorithm = AcceptEncodingAlgorithm.resolve(encoding);
				else
					algorithm = AcceptEncodingAlgorithm.resolve(value);
				
				if (algorithm != null) {
					if (StringUtils.isNotBlank(weight)) 
						list.add(new AcceptEncoding(algorithm, Double.parseDouble(weight)));
					else
						list.add(new AcceptEncoding(algorithm));
				}
			}
			return list;
		}
		
		return null;
	}
		
	/**
	 * 设置客户端可以理解的自然语言(Accept-Language)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param acceptLanguages
	 */
	public void setAcceptLanguage(AcceptLanguage acceptLanguage) {
		set(HttpRequestHeadersEnum.ACCEPT_LANGUAGE.getKey(), acceptLanguage != null ? acceptLanguage.toString() : null);
	}
	
	/**
	 * 获取客户端可以理解的自然语言(Accept-Language)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public String getAcceptLanguage() {
		return getFirst(HttpRequestHeadersEnum.ACCEPT_LANGUAGE.getKey());
	}
	
	/**
	 * 设置通知服务器在真正的请求中采用的请求头列表(Access-Control-Request-Headers)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param requestHeaders
	 */
	public void setAccessControlRequestHeaders(List<String> requestHeaders) {
		set(HttpRequestHeadersEnum.ACCESS_CONTROL_REQUEST_HEADERS.getKey(), CollectionUtils.join(requestHeaders, VALUE_SEPARATOR));
	}
	
	/**
	 * 获取通知服务器在真正的请求中采用的请求头列表(Access-Control-Request-Headers)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public List<String> getAccessControlRequestHeaders() {
		String first = getFirst(HttpRequestHeadersEnum.ACCESS_CONTROL_REQUEST_HEADERS.getKey());
		return ArrayUtils.toList(StringUtils.split(first, VALUE_SEPARATOR));
	}
	
	/**
	 * 设置服务器在真正的请求中会采用的HTTP方法(Access-Control-Request-Method)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param method
	 */
	public void setAccessControlRequestMethod(HttpMethod method) {
		set(HttpRequestHeadersEnum.ACCESS_CONTROL_REQUEST_METHOD.getKey(), method != null ? method.name() : null);
	}
	
	/**
	 * 获取服务器在真正的请求中会采用的HTTP方法(Access-Control-Request-Method)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public HttpMethod getAccessControlRequestMethod() {
		return HttpMethod.resolve(getFirst(HttpRequestHeadersEnum.ACCESS_CONTROL_REQUEST_METHOD.getKey()));
	}
		
	/**
	 * 设置服务器用于验证用户代理身份的凭证(Authorization)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param authorization
	 */
	public void setAuthorization(Authorization authorization) {
		set(HttpRequestHeadersEnum.AUTHORIZATION.getKey(), authorization != null ? authorization.toString() : null);
	}
	
	/**
	 * 获取服务器用于验证用户代理身份的凭证(Authorization)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public Authorization getAuthorization() {
		String first = getFirst(HttpRequestHeadersEnum.AUTHORIZATION.getKey());
		String type = StringUtils.beforeFrist(first, StringUtils.SPACE);
		String credentials = StringUtils.afterFrist(first, StringUtils.SPACE);
		
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(credentials)) {
			AuthenticationType authenticationType = AuthenticationType.resolve(type);
			if (authenticationType != null)
				return new Authorization(authenticationType, credentials);
		}
		
		return null;
	}
		
	/**
	 * 设置Content-Length
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param contentLength
	 */
	public void setContentLength(long contentLength) {
		set(HttpRequestHeadersEnum.CONTENT_LENGTH.getKey(), Long.toString(contentLength));
	}
	
	/**
	 * 获取Content-Length
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public long getContentLength() {
		String value = getFirst(HttpRequestHeadersEnum.CONTENT_LENGTH.getKey());
		return value != null ? Long.parseLong(value) : -1;
	}
		
	/**
	 * 设置Cookie
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param cookies
	 */
	public void setCookie(Parameters<String, Object> cookies) {
		set(HttpRequestHeadersEnum.COOKIE.getKey(), cookies != null
				? MapUtils.join(cookies.getParameterItems(), StringUtils.ASSIGNMENT, NAME_VALUE_PAIR_SEPARATOR) : null);
	}
	
	/**
	 * 设置Cookie
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public Parameters<String, Object> getCookie() {
		String first = getFirst(HttpRequestHeadersEnum.COOKIE.getKey());
		String[] nameValuePairs = StringUtils.split(first, NAME_VALUE_PAIR_SEPARATOR);
		
		if (ArrayUtils.isNotEmpty(nameValuePairs)) {
			Parameters<String, Object> cookie = new DefaultParameters<String, Object>();
			KeyValuePair<String, String> keyValuePair;
			for (String nameValuePair : nameValuePairs) {
				keyValuePair = StringUtils.splitToKeyValuePair(nameValuePair, StringUtils.ASSIGNMENT);
				if (keyValuePair != null)
					cookie.add(keyValuePair.getKey(), keyValuePair.getValue());
			}
			return cookie;
		}
		
		return null;
	}
	
	/**
	 * 设置是否禁止目标站点对个人信息进行追踪(更注重个人隐私还是定制化内容)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param dnt 1:禁止,0:允许
	 */
	public void setDNT(int dnt) {
		set(HttpRequestHeadersEnum.DNT.getKey(), String.valueOf(NumberUtils.rangeLimit(dnt, 0, 1)));
	}
	
	/**
	 * 设置是否禁止目标站点对个人信息进行追踪(更注重个人隐私还是定制化内容)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public int getDNT() {
		return NumberUtils.rangeLimit(NumberUtils.toIntegerValue(getFirst(HttpRequestHeadersEnum.DNT.getKey())), 0, 1)  ;
	}
	
	/**
	 * 设置期望条件
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param expect
	 */
	public void setExpect(String expect) {
		set(HttpRequestHeadersEnum.EXPECT.getKey(), expect);
	}
	
	/**
	 * 获取期望条件
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public String getExpect() {
		return getFirst(HttpRequestHeadersEnum.EXPECT.getKey());
	}
	
	/**
	 * 设置代理转发信息
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param forwarded
	 */
	public void setForwarded(Forwarded forwarded) {
		set(HttpRequestHeadersEnum.FORWARDED.getKey(), forwarded != null ? forwarded.toString() : null);
	}
	
	/**
	 * 获取代理转发信息
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public Forwarded getForwarded() {
		String first = getFirst(HttpRequestHeadersEnum.FORWARDED.getKey());
		String[] nameValuePairs = StringUtils.split(first, NAME_VALUE_PAIR_SEPARATOR);
		
		if (ArrayUtils.isNotEmpty(nameValuePairs)) {
			Forwarded forwarded = new Forwarded();
			for (String nameValuePair : nameValuePairs) {
				if (StringUtils.startsWithIgnoreCase(nameValuePair, "by=")) {
					forwarded.setBy(StringUtils.afterFristIgnoreCase(nameValuePair, "by="));
					continue;
				}
				
				if (StringUtils.startsWithIgnoreCase(nameValuePair, "for=")) {
					String[] forItems = nameValuePair.split(VALUE_SEPARATOR);
					for (String forItem : forItems) {
						forwarded.addFor(StringUtils.afterFristIgnoreCase(forItem, "for="));
					}
					continue;
				}
				
				if (StringUtils.startsWithIgnoreCase(nameValuePair, "host=")) {
					forwarded.setHost(StringUtils.afterFristIgnoreCase(nameValuePair, "host="));
					continue;
				}
				
				if (StringUtils.startsWithIgnoreCase(nameValuePair, "proto=")) {
					forwarded.setProtocol(HttpProtocol.resolve(StringUtils.afterFristIgnoreCase(nameValuePair, "proto=")));
					continue;
				}
			}
			
			return forwarded;
		}
		
		return null;
	}
	
	/**
	 * 设置来源(属于发送请求的用户电子邮箱地址)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param expect
	 */
	public void setFrom(String from) {
		set(HttpRequestHeadersEnum.FROM.getKey(), from);
	}
	
	/**
	 * 获取来源(属于发送请求的用户电子邮箱地址)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public String getFrom() {
		return getFirst(HttpRequestHeadersEnum.FROM.getKey());
	}
	
	/**
	 * 设置服务器域名
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param host
	 */
	public void setHost(String host) {
		set(HttpRequestHeadersEnum.HOST.getKey(), host);
	}
	
	/**
	 * 获取服务器域名
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public String gethost() {
		return getFirst(HttpRequestHeadersEnum.HOST.getKey());
	}
	
	/**
	 * 设置条件请求(当资源的eTag属性值与列表中的值相匹配时返回该资源)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param eTagValues
	 */
	public void setIfMatch(List<String> eTagValues) {
		set(HttpRequestHeadersEnum.IF_MATCH.getKey(), CollectionUtils.join(eTagValues, VALUE_SEPARATOR));
	}
	
	/**
	 * 获取条件请求(当资源的eTag属性值与列表中的值相匹配时返回该资源)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public List<String> getIfMatch() {
		String first = getFirst(HttpRequestHeadersEnum.IF_MATCH.getKey());
		return ArrayUtils.toList(StringUtils.split(first, VALUE_SEPARATOR));
	}
	
	/**
	 * 设置资源返回的日期时间条件
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param date
	 */
	public void setIfModifiedSince(Date date) {
		set(HttpRequestHeadersEnum.IF_MODIFIED_SINCE.getKey(), date != null ? DateUtils.getGMTDateFormat(Locale.US).format(date) : null);
	}
	
	/**
	 * 获取资源返回的日期时间条件
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public Date getIfModifiedSince() {
		String first = getFirst(HttpRequestHeadersEnum.IF_MODIFIED_SINCE.getKey());
		return StringUtils.isNotBlank(first) ? DateUtils.getGMTDateFormat(Locale.US).parse(first, new ParsePosition(0)) : null;
	}
	
	/**
	 * 设置条件请求(当资源的eTag属性值与列表中的任何值不匹配时返回该资源)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param eTagValues
	 */
	public void setIfNoneMatch(List<String> eTagValues) {
		set(HttpRequestHeadersEnum.IF_NONE_MATCH.getKey(), CollectionUtils.join(eTagValues, VALUE_SEPARATOR));
	}
	
	/**
	 * 获取条件请求(当资源的eTag属性值与列表中的任何值不匹配时返回该资源)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public List<String> getIfNoneMatch() {
		String first = getFirst(HttpRequestHeadersEnum.IF_NONE_MATCH.getKey());
		return ArrayUtils.toList(StringUtils.split(first, VALUE_SEPARATOR));
	}
	
	/**
	 * 设置条件请求(当字段值中的条件得到满足时，Range头字段才会起作用，同时服务器回复206 部分内容状态码，以及Range 头字段请求的相应部分；
	 * 如果字段值中的条件没有得到满足，服务器将会返回 200 OK 状态码，并返回完整的请求资源)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param range
	 */
	public void setIfRange(Object range) {
		if (range instanceof Date)
			set(HttpRequestHeadersEnum.IF_RANGE.getKey(), DateUtils.getGMTDateFormat(Locale.US).format(range));
		else
			set(HttpRequestHeadersEnum.IF_RANGE.getKey(), range != null ? range.toString() : null);
	}
	
	/**
	 * 获取条件请求(当字段值中的条件得到满足时，Range头字段才会起作用，同时服务器回复206 部分内容状态码，以及Range 头字段请求的相应部分；
	 * 如果字段值中的条件没有得到满足，服务器将会返回 200 OK 状态码，并返回完整的请求资源)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public Object getIfRange() {
		String first = getFirst(HttpRequestHeadersEnum.IF_RANGE.getKey());
		if (StringUtils.isNotBlank(first)) {
			Date date = DateUtils.getGMTDateFormat(Locale.US).parse(first, new ParsePosition(0));
			if (date != null)
				return date;
		}
		
		return first;
	}
	
	/**
	 * 设置条件请求(只有当资源在指定的时间之后没有进行过修改的情况下，服务器才会返回请求的资源，或是接受 POST或其他non-safe方法的请求)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param date
	 */
	public void setIfUnmodifiedSince(Date date) {
		set(HttpRequestHeadersEnum.IF_UNMODIFIED_SINCE.getKey(), date != null ? DateUtils.getGMTDateFormat(Locale.US).format(date) : null);
	}
	
	/**
	 * 获取条件请求(只有当资源在指定的时间之后没有进行过修改的情况下，服务器才会返回请求的资源，或是接受 POST或其他non-safe方法的请求)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public Date getIfUnmodifiedSince() {
		String first = getFirst(HttpRequestHeadersEnum.IF_UNMODIFIED_SINCE.getKey());
		return StringUtils.isNotBlank(first) ? DateUtils.getGMTDateFormat(Locale.US).parse(first, new ParsePosition(0)) : null;
	}
	
	/**
	 * 设置请求来自于哪个站点
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param url
	 */
	public void setOrigin(URL url) {
		set(HttpRequestHeadersEnum.ORIGIN.getKey(), url != null ? url.toString() : null);
	}
	
	/**
	 * 获取请求来自于哪个站点
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public URL getOrigin() {
		String first = getFirst(HttpRequestHeadersEnum.ORIGIN.getKey());
		try {
			return StringUtils.isNotBlank(first) ? new URL(first) : null;
		} catch (MalformedURLException e) {
			return null;
		}
	}
	
	/**
	 * 设置提供给代理服务器的用于验证用户身份的凭证(Proxy-Authorization)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param authorization
	 */
	public void setProxyAuthorization(Authorization authorization) {
		set(HttpRequestHeadersEnum.PROXY_AUTHORIZATION.getKey(), authorization != null ? authorization.toString() : null);
	}
		
	/**
	 * 获取提供给代理服务器的用于验证用户身份的凭证(Proxy-Authorization)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public Authorization getProxyAuthorization() {
		String first = getFirst(HttpRequestHeadersEnum.PROXY_AUTHORIZATION.getKey());
		String type = StringUtils.beforeFrist(first, StringUtils.SPACE);
		String credentials = StringUtils.afterFrist(first, StringUtils.SPACE);
		
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(credentials)) {
			AuthenticationType authenticationType = AuthenticationType.resolve(type);
			if (authenticationType != null)
				return new Authorization(authenticationType, credentials);
		}
		
		return null;
	}
	
	/**
	 * 设置服务器返回的文件范围
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param range
	 */
	public void setRange(Range range) {
		set(HttpRequestHeadersEnum.RANGE.getKey(), range != null ? range.toString() : null);
	}
	
	/**
	 * 获取服务器返回的文件范围
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public Range getRange() {
		String first = getFirst(HttpRequestHeadersEnum.RANGE.getKey());
		String unit = StringUtils.beforeFrist(first, StringUtils.ASSIGNMENT);
		String[] rangeItems = StringUtils.split(StringUtils.afterFrist(first, StringUtils.ASSIGNMENT), VALUE_SEPARATOR);  
		
		if (StringUtils.isNotBlank(unit) && ArrayUtils.isNotEmpty(rangeItems)) {
			List<RangeValue> rangeValues = CollectionUtils.newArrayList(rangeItems.length);
			for (String rangeItem : rangeItems) {
				Long start = NumberUtils.toLong(StringUtils.beforeFrist(rangeItem, StringUtils.CONNECTION_LINE));
				if (start != null)
					rangeValues.add(new RangeValue(start, NumberUtils.toLong(StringUtils.afterFrist(rangeItem, StringUtils.CONNECTION_LINE))));
			}
			return new Range(unit, rangeValues);
		}
		
		return null;
	}
	
	/**
	 * 设置当前页面的来源页面的地址
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param url
	 */
	public void setReferer(URL url) {
		set(HttpRequestHeadersEnum.REFERER.getKey(), url != null ? url.toString() : null);
	}
	
	/**
	 * 获取当前页面的来源页面的地址
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public URL getReferer() {
		String first = getFirst(HttpRequestHeadersEnum.REFERER.getKey());
		try {
			return StringUtils.isNotBlank(first) ? new URL(first) : null;
		} catch (MalformedURLException e) {
			return null;
		}
	}
	
	/**
	 * 设置用户代理希望使用的传输编码类型
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param te
	 */
	public void setTE(List<TE> tes) {
		set(HttpRequestHeadersEnum.TE.getKey(), CollectionUtils.join(tes, VALUE_SEPARATOR));
	}
	
	/**
	 * 获取用户代理希望使用的传输编码类型
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public List<TE> getTE() {
		String first = getFirst(HttpRequestHeadersEnum.TE.getKey());
		String[] values = StringUtils.split(first, VALUE_SEPARATOR);

		if (ArrayUtils.isNotEmpty(values)) {
			List<TE> list = CollectionUtils.newArrayList();
			String weight;
			String encoding;
			TEAlgorithm algorithm;
			for (String value : values) {
				weight = StringUtils.afterLast(value, StringUtils.ASSIGNMENT);
				encoding = StringUtils.beforeFrist(value, StringUtils.SEMICOLON);

				if (StringUtils.isNotEmpty(encoding))
					algorithm = TEAlgorithm.resolve(encoding);
				else
					algorithm = TEAlgorithm.resolve(value);

				if (algorithm != null) {
					if (StringUtils.isNotBlank(weight)) 
						list.add(new TE(algorithm, Double.parseDouble(weight)));
					else
						list.add(new TE(algorithm));
				}
			}
			return list;
		}
		
		return null;
	}
	
	/**
	 * 设置客户端优先选择加密及带有身份验证的响应(Upgrade-Insecure-Requests)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param value
	 */
	public void setUpgradeInsecureRequests(String value) {
		set(HttpRequestHeadersEnum.UPGRADE_INSECURE_REQUESTS.getKey(), value);
	}
	
	/**
	 * 获取客户端优先选择加密及带有身份验证的响应(Upgrade-Insecure-Requests)
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public String getUpgradeInsecureRequests() {
		return getFirst(HttpRequestHeadersEnum.UPGRADE_INSECURE_REQUESTS.getKey());
	}
	
	/**
	 * 设置用户代理
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param userAgent
	 */
	public void setUserAgent(String userAgent) {
		set(HttpRequestHeadersEnum.USER_AGENT.getKey(), userAgent);
	}
	
	/**
	 * 获取用户代理
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public String getUserAgent() {
		return getFirst(HttpRequestHeadersEnum.USER_AGENT.getKey());
	}
	
	/**
	 * 设置X-Forwarded-For请求消息头 
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param ips
	 */
	public void setXForwardedFor(List<String> ips) {
		set(HttpRequestHeadersEnum.X_FORWARDED_FOR.getKey(), CollectionUtils.join(ips, VALUE_SEPARATOR));
	}
	
	/**
	 * 获取X-Forwarded-For请求消息头
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public List<String> getXForwardedFor() {
		String first = getFirst(HttpRequestHeadersEnum.X_FORWARDED_FOR.getKey());
		String[] values = StringUtils.split(first, VALUE_SEPARATOR);

		if (ArrayUtils.isNotEmpty(values)) {
			List<String> list = CollectionUtils.newArrayList();
			for (String value : values) {
				if (StringUtils.isNotBlank(value))
					list.add(value);
			}
			return list;
		}
		
		return null;
	}
	
	/**
	 * 设置X-Forwarded-Host请求消息头
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param host
	 */
	public void setXForwardedHost(String host) {
		set(HttpRequestHeadersEnum.X_FORWARDED_HOST.getKey(), host);
	}
	
	/**
	 * 获取X-Forwarded-Host请求消息头
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public String getXForwardedHost() {
		return getFirst(HttpRequestHeadersEnum.X_FORWARDED_HOST.getKey());
	}
	
	/**
	 * 设置X-Forwarded-Proto请求消息头
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param protocol
	 */
	public void setXForwardedProto(HttpProtocol protocol) {
		set(HttpRequestHeadersEnum.X_FORWARDED_PROTO.getKey(), protocol != null ? protocol.name().toLowerCase() : null);
	}
	
	/**
	 * 获取X-Forwarded-Proto请求消息头
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @return
	 */
	public HttpProtocol getXForwardedProto() {
		return HttpProtocol.resolve(getFirst(HttpRequestHeadersEnum.X_FORWARDED_PROTO.getKey()));
	}
	
}
