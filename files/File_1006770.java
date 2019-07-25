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
package x7.core.config;

import x7.core.bean.Config;

import java.util.List;
import java.util.Map;

public class ConfigRefresher {

	public static void refresh(List<Config> list){
		
		Map<String, Object> map = Configs.getMap(null);
		if (map == null)
			return;
//		System.out.println("config list = " + list);
		for (Config config : list){
			if (config == null || config.getKeyX() == null)
				continue;
			map.put(config.getKeyX(), config.getValue());
		}
	}
}
