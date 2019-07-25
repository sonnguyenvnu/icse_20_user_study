/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package x7.repository.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import x7.config.SpringHelper;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * �?止用flushall命令<br>
 * 
 * @author Sim
 *
 */
public class JedisConnector_Persistence {

	private RedisTemplate redisTemplate;

	private StringRedisTemplate stringRedisTemplate;

	private static JedisConnector_Persistence instance;

	public static JedisConnector_Persistence getInstance() {
		if (instance == null) {
			instance = new JedisConnector_Persistence();
		}
		return instance;
	}

	private void init(){
		redisTemplate = (RedisTemplate) SpringHelper.getObject("redisTemplate");
		stringRedisTemplate = (StringRedisTemplate)SpringHelper.getObject("stringRedisTemplate");
	}

	private JedisConnector_Persistence() {
		init();
	}



	public boolean set(String key, String value) {
		if (key == null || key.equals("") )
			return false;
		this.stringRedisTemplate.opsForValue().set(key, value);
		return true;
	}

	public boolean set(byte[] key, byte[] value, int validSeconds){

		this.redisTemplate.opsForValue().set(key, value,validSeconds);
		return true;
	}

	public void set(String key, String value, int seconds) {
		this.stringRedisTemplate.opsForValue().set(key,value,seconds);
	}



	public void set(byte[] key, byte[] value ) {
		this.redisTemplate.opsForValue().set(key,value);
	}

	public String get(String key) {

		String str = this.stringRedisTemplate.opsForValue().get(key);
		if (str == null)
			return str;
		return str.trim();
	}
	public List<byte[]> mget(List<byte[]> keyList){

		if (keyList == null || keyList.isEmpty())
			return null;

		List<byte[]> byteList = this.redisTemplate.opsForValue().multiGet(keyList);

		return byteList;
	}

	public byte[] get(byte[] key) {

		Object obj = this.redisTemplate.opsForValue().get(key);

		return (byte[]) obj;
	}
	
	public void delete(String key) {

		this.stringRedisTemplate.delete(key);

	}

	public void delete(byte[] key) {

		this.redisTemplate.delete(key);

	}

	public void hset(String mapName, String key, String value) {
		this.stringRedisTemplate.opsForHash().put(mapName,key,value);
	}

	public String hget(String mapName, String key) {

		Object obj = this.stringRedisTemplate.opsForHash().get(mapName,key);

		if (obj== null)
			return null;
		return obj.toString().trim();
	}


	public long hincrBy(String mapName, String key, long increment) {
		return this.stringRedisTemplate.opsForHash().increment(mapName,key,increment);
	}
	
	public boolean lock(String key){
		
		boolean isLock;
		
		final String value = "LOCK";

		isLock = this.stringRedisTemplate.opsForValue().setIfAbsent(key, value);
		if (isLock) {
			this.stringRedisTemplate.expire(key,5, TimeUnit.SECONDS);
		}

		return isLock;
	}
	
	public void unLock(String key){
		this.stringRedisTemplate.delete(key);
	}
}
