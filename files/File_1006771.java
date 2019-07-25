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
package x7.core.template;


import x7.core.bean.KV;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * 模�?�管�?�器<br>
 * @author wyan
 *
 */
public class Templates {

	private final static Map<Class<? extends ITemplateable>, Map<Object, ITemplateable>> templatesMap = new ConcurrentHashMap<Class<? extends ITemplateable>, Map<Object, ITemplateable>>();
	
	private final static Map<Class<? extends ITemplateable>, List<KV>> schemaMap  = new ConcurrentHashMap<>();

	
	public static <T> T get(Class<T> clz, Object templateId){
		Map<Object, ? extends ITemplateable> templateMap = templatesMap.get(clz);
		if (templateMap == null)
			return null;
		return (T) templateMap.get(templateId);
	}

	
	public static <T> Map<Object, T> get(Class<T> clz){
		return (Map<Object, T>) templatesMap.get(clz);
	}

	public static void clear() {
		templatesMap.clear();
	}
	
	/**
	 * 加载模�?�时调用，或热更新时调用
	 * @param clz
	 */
	public static Map<Object, ITemplateable> createOrGet(Class<? extends ITemplateable> clz){

		Map<Object, ITemplateable> map = templatesMap.get(clz);
		if (map == null){
			map = new HashMap<Object, ITemplateable>();
			templatesMap.put(clz, map);
		}
		
		return map;
	}
	
	public static void put(Class<? extends ITemplateable> clz, Map<Object, ITemplateable> map ){
		templatesMap.put(clz, map);
	}
	
	public static void put(Class<? extends ITemplateable> key,List<KV> value){
		schemaMap.put(key, value);
	}
	
	public static List<KV> getSchema(Class<? extends ITemplateable> key){
		return schemaMap.get(key);
	}
	
}
