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
package x7.tool;

import x7.core.util.BeanUtil;
import x7.core.util.ClassFileReader;
import x7.tool.bean.BeanTemplate;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;


public class BeanKey {

	public static Set<BeanTemplate> createTemplateSet() {

		Set<Class<?>> set = ClassFileReader.getClasses(Config.PKG);

		Set<BeanTemplate> st = new HashSet<>();
		for (Class<?> clz : set) {
			BeanTemplate template = parse(clz);
			if (template == null)
				continue;
			if (template.getClzName() == null ||template.getClzName().equals("") || template.getClzName().equals("Key"))
				continue;
			st.add(template);
		}

		return st;
	}

	public static BeanTemplate parse(Class<?> clz) {

		BeanTemplate template = new BeanTemplate();
		
		String fullName = clz.getName();
		String simpleName = clz.getSimpleName();
		simpleName = BeanUtil.getByFirstLower(simpleName);
		String pkg = "key." + fullName.substring(0, fullName.lastIndexOf("."));
		template.setPackageName(pkg);
		template.setClzName(simpleName);

		Field[] fields = clz.getDeclaredFields();

		for (Field field : fields) {
			
			if (field.getModifiers() > 2) 
				continue;

			template.getPropList().add(field.getName());
		}
		
		System.out.println(template);

		return template;
	}

}
