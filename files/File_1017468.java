package com.zone.weixin4j.util;

import com.zone.weixin4j.base64.Base64;
import com.zone.weixin4j.exception.WeixinException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

/**
 * 消�?�工具类
 * 
 * @className MessageUtil
 * @author jinyu(foxinmy@gmail.com)
 * @date 2014年10月31日
 * @since JDK 1.6
 * @see
 */
public final class MessageUtil {
	/**
	 * 验�?微信签�??
	 * 
	 * @param signature
	 *            微信加密签�??，signature结�?�了开�?�者填写的token�?�数和请求中的timestamp�?�数�?nonce�?�数
	 * @return 开�?�者通过检验signature对请求进行相关校验。若确认此次GET请求�?�自微信�?务器
	 *         请原样返回echostr�?�数内容，则接入生效 �?为开�?�者�?功，�?�则接入失败
	 * @see <a
	 *      href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421135319&token=&lang=zh_CN">接入指�?�</a>
	 */
	public static String signature(String... para) {
		Arrays.sort(para);
		StringBuffer sb = new StringBuffer();
		for (String str : para) {
			sb.append(str);
		}
		return ServerToolkits.digestSHA1(sb.toString());
	}

	/**
	 * 对xml消�?�加密
	 * 
	 * @param appId
	 *            应用ID
	 * @param encodingAesKey
	 *            加密密钥
	 * @param xmlContent
	 *            原始消�?�体
	 * @return aes加密�?�的消�?�体
	 * @throws WeixinException
	 */
	public static String aesEncrypt(String appId, String encodingAesKey,
			String xmlContent) throws WeixinException {
		/**
		 * 其中，msg_encrypt=Base64_Encode(AES_Encrypt [random(16B)+ msg_len(4B) +
		 * msg + $AppId])
		 * 
		 * random(16B)为16字节的�?机字符串；msg_len为msg长度，�?�4个字节(网络字节�?)，$AppId为公众账�?�的AppId
		 */
		byte[] randomBytes = ServerToolkits.getBytesUtf8(ServerToolkits
				.generateRandomString(16));
		byte[] xmlBytes = ServerToolkits.getBytesUtf8(xmlContent);
		int xmlLength = xmlBytes.length;
		byte[] orderBytes = new byte[4];
		orderBytes[3] = (byte) (xmlLength & 0xFF);
		orderBytes[2] = (byte) (xmlLength >> 8 & 0xFF);
		orderBytes[1] = (byte) (xmlLength >> 16 & 0xFF);
		orderBytes[0] = (byte) (xmlLength >> 24 & 0xFF);
		byte[] appidBytes = ServerToolkits.getBytesUtf8(appId);

		int byteLength = randomBytes.length + xmlLength + orderBytes.length
				+ appidBytes.length;
		// ... + pad: 使用自定义的填充方�?对明文进行补�?填充
		byte[] padBytes = PKCS7Encoder.encode(byteLength);
		// random + endian + xml + appid + pad 获得最终的字节�?
		byte[] unencrypted = new byte[byteLength + padBytes.length];
		byteLength = 0;
		// src:�?数组;srcPos:�?数组�?�?制的起始�?置;dest:目的数组;destPos:目的数组放置的起始�?置;length:�?制的长度
		System.arraycopy(randomBytes, 0, unencrypted, byteLength,
				randomBytes.length);
		byteLength += randomBytes.length;
		System.arraycopy(orderBytes, 0, unencrypted, byteLength,
				orderBytes.length);
		byteLength += orderBytes.length;
		System.arraycopy(xmlBytes, 0, unencrypted, byteLength, xmlBytes.length);
		byteLength += xmlBytes.length;
		System.arraycopy(appidBytes, 0, unencrypted, byteLength,
				appidBytes.length);
		byteLength += appidBytes.length;
		System.arraycopy(padBytes, 0, unencrypted, byteLength, padBytes.length);
		try {
			byte[] aesKey = Base64.decodeBase64(encodingAesKey + "=");
			// 设置加密模�?为AES的CBC模�?
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec keySpec = new SecretKeySpec(aesKey, ServerToolkits.AES);
			IvParameterSpec iv = new IvParameterSpec(aesKey, 0, 16);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);
			// 加密
			byte[] encrypted = cipher.doFinal(unencrypted);
			// 使用BASE64对加密�?�的字符串进行编�?
			// return Base64.encodeBase64String(encrypted);
			return Base64
					.encodeBase64String(encrypted);
		} catch (Exception e) {
			throw new WeixinException("-40006", "AES加密失败:" + e.getMessage());
		}
	}

	/**
	 * 对AES消�?�解密
	 * 
	 * @param appId
	 * @param encodingAesKey
	 *            aes加密的密钥
	 * @param encryptContent
	 *            加密的消�?�体
	 * @return 解密�?�的字符
	 * @throws WeixinException
	 */
	public static String aesDecrypt(String appId, String encodingAesKey,
			String encryptContent) throws WeixinException {
		byte[] aesKey = Base64.decodeBase64(encodingAesKey + "=");
		byte[] original;
		try {
			// 设置解密模�?为AES的CBC模�?
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec key_spec = new SecretKeySpec(aesKey, ServerToolkits.AES);
			IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey,
					0, 16));
			cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);
			// 使用BASE64对密文进行解�?
			byte[] encrypted = Base64.decodeBase64(encryptContent);
			// 解密
			original = cipher.doFinal(encrypted);
		} catch (Exception e) {
			throw new WeixinException("-40007", "AES解密失败:" + e.getMessage());
		}
		String xmlContent, fromAppId;
		try {
			// 去除补�?字符
			byte[] bytes = PKCS7Encoder.decode(original);
			/**
			 * AES加密的buf由16个字节的�?机字符串�?4个字节的msg_len(网络字节�?)�?msg和$AppId组�?，
			 * 其中msg_len为msg的长度，$AppId为公众�?�?�的AppId
			 */
			// 获�?�表示xml长度的字节数组
			byte[] lengthByte = Arrays.copyOfRange(bytes, 16, 20);
			// 获�?�xml消�?�主体的长度(byte[]2int)
			// http://my.oschina.net/u/169390/blog/97495
			int xmlLength = lengthByte[3] & 0xff | (lengthByte[2] & 0xff) << 8
					| (lengthByte[1] & 0xff) << 16
					| (lengthByte[0] & 0xff) << 24;
			xmlContent = ServerToolkits.newStringUtf8(Arrays.copyOfRange(bytes, 20,
					20 + xmlLength));
			fromAppId = ServerToolkits.newStringUtf8(Arrays.copyOfRange(bytes,
					20 + xmlLength, bytes.length));
		} catch (Exception e) {
			throw new WeixinException("-40008", "xml内容�?�?�法:" + e.getMessage());
		}
		// 校验appId是�?�一致
		if (appId != null && !fromAppId.trim().equals(appId)) {
			throw new WeixinException("-40005", "校验AppID失败,expect " + appId
					+ ",but actual is " + fromAppId);
		}
		return xmlContent;
	}
}
