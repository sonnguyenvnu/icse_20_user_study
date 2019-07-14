/*
 * Copyright (C) 2019 the original author or authors.
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
 */
package org.springframework.cloud.alicloud.sms.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 获�?�接收云通信消�?�的临时token
 *
 */
public class TokenGetterForAlicom {
	private static final Logger log = LoggerFactory.getLogger(TokenGetterForAlicom.class);
	private String accessKeyId;
	private String accessKeySecret;
	private String endpointNameForPop;
	private String regionIdForPop;
	private String domainForPop;
	private IAcsClient iAcsClient;
	private Long ownerId;
	private final static String PRODUCT_NAME = "Dybaseapi";
	private long bufferTime = 1000 * 60 * 2;// 过期时间�?于2分钟则�?新获�?�，防止�?务器时间误差
	private final Object lock = new Object();
	private ConcurrentMap<String, TokenForAlicom> tokenMap = new ConcurrentHashMap<String, TokenForAlicom>();

	public TokenGetterForAlicom(String accessKeyId, String accessKeySecret,
			String endpointNameForPop, String regionIdForPop, String domainForPop,
			Long ownerId) throws ClientException {
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.endpointNameForPop = endpointNameForPop;
		this.regionIdForPop = regionIdForPop;
		this.domainForPop = domainForPop;
		this.ownerId = ownerId;
		init();
	}

	private void init() throws ClientException {
		DefaultProfile.addEndpoint(endpointNameForPop, regionIdForPop, PRODUCT_NAME,
				domainForPop);
		IClientProfile profile = DefaultProfile.getProfile(regionIdForPop, accessKeyId,
				accessKeySecret);
		profile.getHttpClientConfig().setCompatibleMode(true);
		iAcsClient = new DefaultAcsClient(profile);
	}

	private TokenForAlicom getTokenFromRemote(String messageType)
			throws ServerException, ClientException, ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		QueryTokenForMnsQueueRequest request = new QueryTokenForMnsQueueRequest();
		request.setAcceptFormat(FormatType.JSON);
		request.setMessageType(messageType);
		request.setOwnerId(ownerId);
		request.setProtocol(ProtocolType.HTTPS);
		request.setMethod(MethodType.POST);
		QueryTokenForMnsQueueResponse response = iAcsClient.getAcsResponse(request);
		String resultCode = response.getCode();
		if (resultCode != null && "OK".equals(resultCode)) {
			QueryTokenForMnsQueueResponse.MessageTokenDTO dto = response
					.getMessageTokenDTO();
			TokenForAlicom token = new TokenForAlicom();
			String timeStr = dto.getExpireTime();
			token.setMessageType(messageType);
			token.setExpireTime(df.parse(timeStr).getTime());
			token.setToken(dto.getSecurityToken());
			token.setTempAccessKeyId(dto.getAccessKeyId());
			token.setTempAccessKeySecret(dto.getAccessKeySecret());
			return token;
		}
		else {
			log.error("getTokenFromRemote_error,messageType:" + messageType + ",code:"
					+ response.getCode() + ",message:" + response.getMessage());
			throw new ServerException(response.getCode(), response.getMessage());
		}
	}

	public TokenForAlicom getTokenByMessageType(String messageType, String queueName,
			String mnsAccountEndpoint)
			throws ServerException, ClientException, ParseException {
		TokenForAlicom token = tokenMap.get(messageType);
		Long now = System.currentTimeMillis();
		if (token == null || (token.getExpireTime() - now) < bufferTime) {// 过期时间�?于2分钟则�?新获�?�，防止�?务器时间误差
			synchronized (lock) {
				token = tokenMap.get(messageType);
				if (token == null || (token.getExpireTime() - now) < bufferTime) {
					TokenForAlicom oldToken = null;
					if (token != null) {
						oldToken = token;
					}
					token = getTokenFromRemote(messageType);
					// 因为�?�token时需�?�?建client和关闭�?的client，所以创建client的代�?和创建token放在一起
					CloudAccount account = new CloudAccount(token.getTempAccessKeyId(),
							token.getTempAccessKeySecret(), mnsAccountEndpoint,
							token.getToken());
					MNSClient client = account.getMNSClient();
					CloudQueue queue = client.getQueueRef(queueName);
					token.setClient(client);
					token.setQueue(queue);
					tokenMap.put(messageType, token);
					if (oldToken != null) {
						oldToken.closeClient();
					}
				}
			}
		}
		return token;
	}
}
