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
 * Create Date : 2015-11-3
 */

package org.workin.fastdfs.support;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerServer;
import org.workin.commons.util.AssertUtils;
import org.workin.commons.util.CollectionUtils;
import org.workin.commons.util.StringUtils;
import org.workin.fastdfs.cluster.Cluster;
import org.workin.fastdfs.factory.connection.ConnectionFactory;
import org.workin.fastdfs.meta.FastDFSMeta;
import org.workin.support.file.ZoomResource;

/**
 * @description FastDFS模板实现类
 * @author  <a href="mailto:code727@gmail.com">杜斌</a>
 * @version 1.0
 */
public class FastDFSTemplet extends FastDFSSupport implements FastDFSOperations {
	
	public FastDFSTemplet() {}
	
	public FastDFSTemplet(Cluster cluster, ConnectionFactory connectionFactory) {
		setConnectionFactory(connectionFactory);
	}

	@Override
	public <T> T execute(FastDFSCallback<T> action) throws Exception {
		return execute(null, action);
	}

	@Override
	public <T> T execute(String groupName, FastDFSCallback<T> action) throws Exception {
		return execute(null, action, true);
	}
	
	@Override
	public <T> T execute(String groupName, FastDFSCallback<T> action, boolean autoRelease) throws Exception {
		AssertUtils.assertNotNull(action, "Callback object must not be null");
		
		TrackerServer trackerServer = null;
		StorageServer storageServer = null;
		try {
			trackerServer = connectionFactory.getTrackerServer();
			storageServer = connectionFactory.getStorageServer(trackerServer, StringUtils.trimToEmpty(groupName));
			return action.doIn(new StorageClient1(trackerServer, storageServer));
		} finally {
			if (autoRelease)
				connectionFactory.release(trackerServer, storageServer);
		}
	}

	@Override
	public <T> String upload(FastDFSMeta<T> meta) throws Exception {
		return upload(null, meta);
	}

	@Override
	public <T> String upload(String groupName, FastDFSMeta<T> meta) throws Exception {
		List<FastDFSMeta<T>> metas = CollectionUtils.newArrayList();
		metas.add(meta);
		return CollectionUtils.get(batchUpload(groupName, metas, false), 0);
	}

	@Override
	public <T> String reupload(FastDFSMeta<T> meta) throws Exception {
		return reupload(null, meta);
	}

	@Override
	public <T> String reupload(String groupName, FastDFSMeta<T> meta) throws Exception {
		List<FastDFSMeta<T>> metas = CollectionUtils.newArrayList();
		metas.add(meta);
		return CollectionUtils.get(batchUpload(groupName, metas, true), 0);
	}

	@Override
	public <T> List<String> batchUpload(List<FastDFSMeta<T>> metas) throws Exception {
		return batchUpload(null, metas);
	}

	@Override
	public <T> List<String> batchUpload(String groupName, List<FastDFSMeta<T>> metas) throws Exception {
		return batchUpload(groupName, metas, false);
	}

	@Override
	public <T> List<String> batchReupload(List<FastDFSMeta<T>> metas) throws Exception {
		return batchReupload(null, metas);
	}

	@Override
	public <T> List<String> batchReupload(String groupName, List<FastDFSMeta<T>> metas) throws Exception {
		return batchUpload(groupName, metas, true);
	}

	@Override
	public <T> ZoomResource srcZoomUpload(FastDFSMeta<T> meta) throws Exception {
		return srcZoomUpload(null, meta);
	}

	@Override
	public <T> ZoomResource srcZoomUpload(String groupName, FastDFSMeta<T> meta) throws Exception {
		List<FastDFSMeta<T>> metas = CollectionUtils.newArrayList();
		metas.add(meta);
		return CollectionUtils.get(srcZoomBatchUpload(groupName, metas, false), 0);
	}

	@Override
	public <T> ZoomResource srcZoomReupload(FastDFSMeta<T> meta) throws Exception {
		return srcZoomReupload(null, meta);
	}

	@Override
	public <T> ZoomResource srcZoomReupload(String groupName, FastDFSMeta<T> meta) throws Exception {
		List<FastDFSMeta<T>> metas = CollectionUtils.newArrayList();
		metas.add(meta);
		return CollectionUtils.get(srcZoomBatchUpload(groupName, metas, true), 0);
	}

	@Override
	public <T> List<ZoomResource> srcZoomBatchUpload(List<FastDFSMeta<T>> metas) throws Exception {
		return srcZoomBatchUpload(null, metas);
	}

	@Override
	public <T> List<ZoomResource> srcZoomBatchUpload(String groupName, List<FastDFSMeta<T>> metas) throws Exception {
		return srcZoomBatchUpload(groupName, metas, false);
	}

	@Override
	public <T> List<ZoomResource> srcZoomBatchReupload(List<FastDFSMeta<T>> metas) throws Exception {
		return srcZoomBatchReupload(null, metas);
	}

	@Override
	public <T> List<ZoomResource> srcZoomBatchReupload(final String groupName, final List<FastDFSMeta<T>> metas) throws Exception {
		return srcZoomBatchUpload(groupName, metas, true);
	}
	
	/**
	 * @description 批量上传资源到指定组，并指定完成后是否删除旧资源
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param groupName
	 * @param metas
	 * @param deleteOriginalResource
	 * @return
	 * @throws Exception
	 */
	private <T> List<String> batchUpload(final String groupName, final List<FastDFSMeta<T>> metas, final boolean deleteOriginalResource) throws Exception {
		return this.execute(groupName, new FastDFSCallback<List<String>>() {

			@Override
			public List<String> doIn(StorageClient1 storageClient) throws Exception {
				List<String> list = doBatchUpload(storageClient, groupName, metas);
				// TODO 后续改为用线程池来执行
				if (deleteOriginalResource) {
					for (FastDFSMeta<T> meta : metas) {
						storageClient.delete_file1(meta.getOriginalId());
					}
				}
				return list;
			}
		}, !deleteOriginalResource);
	}
	
	/**
	 * @description 批量上传源以及缩放后的资源到指定组，并指定完成后是否删除旧资源
	 * @author <a href="mailto:code727@gmail.com">杜斌</a> 
	 * @param groupName
	 * @param metas
	 * @param deleteOriginalResource
	 * @return
	 * @throws Exception
	 */
	private <T> List<ZoomResource> srcZoomBatchUpload(final String groupName, final List<FastDFSMeta<T>> metas, final boolean deleteOriginalResource) throws Exception {
		return this.execute(groupName, new FastDFSCallback<List<ZoomResource>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<ZoomResource> doIn(StorageClient1 storageClient) throws Exception {
				String targetGroupName = StringUtils.trimToEmpty(groupName);
				Map<String, Object> map = doSrcZoomBatchUpload(storageClient, targetGroupName, metas);
				List<File> tempImageSources = (List<File>) map.get("tempImageSources");
				
				// TODO 后续改为用线程池来执行
				if (deleteOriginalResource) {
					for (FastDFSMeta<T> meta : metas) {
						storageClient.delete_file1(meta.getOriginalId());
						storageClient.delete_file1(meta.getOriginalZoomId());
					}
				}
				
				// TODO 后续改为用线程池来执行
				for (File tempImage : tempImageSources)
					tempImage.delete();
					
				return (List<ZoomResource>) map.get("zoomResources");
			}
		}, !deleteOriginalResource);
	}

}