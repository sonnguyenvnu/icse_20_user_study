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
package x7.config;

import x7.core.config.Configs;
import x7.core.util.KeyUtil;
import x7.core.util.StringUtil;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class TextParser{
	
	private static TextParser instance = null;

	private static String[] configFileSuffixArr = {".txt",".cfg",".properties",".cnf",".js",".json"};
	
	public static TextParser getInstance(){
		if (instance == null){
			instance = new TextParser();
		}
		return instance;
	}
	private TextParser(){

	}

	private Map<String, Object> map = null;


	private void readConfig(JarFile jf, JarEntry je){

		try {
			InputStream is = jf.getInputStream(je);

			BufferedReader br=null;
			try {

				br=new BufferedReader(new InputStreamReader(is,"utf-8"));
				String dataStr="";

				String key = null;
				String value = null;

				while((dataStr=br.readLine())!=null){

					if (dataStr.startsWith("#"))
						continue;
					if(dataStr.contains("=")){

						put (key, value);
						key = null;
						value = null;

						//等�?�左边为key，等�?��?�边为value
						key=dataStr.substring(0,dataStr.indexOf("=")).trim();
						value=dataStr.substring(dataStr.indexOf("=")+1);


					}else{
						if (StringUtil.isNullOrEmpty(dataStr))
							continue;
						if (dataStr.equals("\n"))
							continue;
						value += "\n";
						value += dataStr;
					}

				}

				put (key, value);
				key = null;
				value = null;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					br.close();
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public void load(String[] ativeProfiles){

		map = Configs.referMap();

		String path = this.getClass().getClassLoader().getResource("").getPath();

		if (path.contains(".jar")){
			// file:/data/deploy/ruhr-web-dev/ruhr-web-dev-1.0.0.jar!/BOOT-INF/classes!/
			String[] arr = path.split(":");
			String real = arr[1];

			String[] pathFolder = real.split("!");

			String jarPath = pathFolder[0];

			String jarFolder = pathFolder[1];
			jarFolder = jarFolder.substring(1);

			{
				final String testPathTag = "test-classes";
				final String appPathTag ="classes";
				if (jarFolder.contains(testPathTag)){
					jarFolder = jarFolder.replace(testPathTag,appPathTag);
				}
			}

			try {
				JarFile jarFile = new JarFile(jarPath);

				Enumeration<JarEntry> es = jarFile.entries();

				while (es.hasMoreElements()){
					JarEntry j = es.nextElement();
					String name = j.getName();

					if (!name.startsWith(jarFolder))
						continue;

					name = name.replace(jarFolder,"");

					boolean ignored = true;
					for (String suffix : configFileSuffixArr) {
						if (name.endsWith(suffix)) {
							ignored = false;
							break;
						}
					}

					if (ignored)
						continue;

					if (ativeProfiles == null || ativeProfiles.length==0){
						System.out.println("\n[" +  name+ "]");
						readConfig(jarFile, j);
						continue;
					}else {

						if (name.equals("application.properties"))
							continue;

						if (!name.contains("-")) {
							System.out.println("\n[" + name + "]");
							readConfig(jarFile, j);
							continue;
						}

						for (String profile : ativeProfiles) {
							if (name.contains("-" + profile + ".")) {
								System.out.println("\n[" + name + "]");
								readConfig(jarFile, j);
								break;
							}
						}
						continue;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {

			try {

				{
					final String testPathTag = "test-classes";
					final String appPathTag ="classes";
					if (path.contains(testPathTag)){
						path = path.replace(testPathTag,appPathTag);
					}
				}

				instance.readConfigs(path, ativeProfiles);
			} catch (Exception e) {
				e.printStackTrace();
				String notes = "无法�?�动";
				System.err.println("\n" + notes + "\n");
				String err = "加载�?置文件出错,请检查�?置文件config/*.txt";
				System.err.println(err + "\n");
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	 
	
	public int getIntValue(String key){
	Integer value = 0;
	
		try{
			value = Integer.valueOf(map.get(key)+""); 
		}catch (MissingResourceException mre){
			String err = "请检查�?置文件config/*.txt, 缺少key:" + key;
			System.err.println(err);
			mre.printStackTrace();
		}catch (Exception e){
			String err = "请检查�?置文件config/*.txt, �?�现了:" + key + "="+map.get(key);
			System.err.println(err);
			e.printStackTrace();
		}
		return value;
	}
	
	public String getString(String key){
		String value = "";
		
		try{
			value = map.get(key)+"";
		}catch (MissingResourceException mre){
			String err = "请检查�?置文件config/*.txt, 缺少key:" + key;
			System.err.println(err);
			mre.printStackTrace();

		}catch (Exception e){
			String err = "请检查�?置文件config/*.txt, �?�现了:" + key + "="+map.get(key);
			System.err.println(err);
			e.printStackTrace();

		}
		return value;
	}
	
	public long getLongValue(String key){
		Long value = 0L;
		
		try{
			value = Long.valueOf(map.get(key)+"");
		}catch (MissingResourceException mre){
			String err = "请检查�?置文件config/*.txt, 缺少key:" + key;
			System.err.println(err);
			mre.printStackTrace();

		}catch (Exception e){
			String err = "请检查�?置文件config/*.txt, �?�现了:" + key + "="+map.get(key);
			System.err.println(err);
			e.printStackTrace();
	
		}
		return value;
	}


	/**
	 × 读�?�文件存入configData中
	 * @param path
	 * @return
	 */
	public void readConfig(String path){
		FileInputStream fis=null;
		BufferedReader br=null;
		try {
			fis=new FileInputStream(path);
			br=new BufferedReader(new InputStreamReader(fis,"utf-8"));
			String dataStr="";

			String key = null;
			String value = null;
			
			while((dataStr=br.readLine())!=null){
				
				if (dataStr.startsWith("#"))
					continue;
				if(dataStr.contains("=")){
					
					put (key, value);
					key = null;
					value = null;
					
					//等�?�左边为key，等�?��?�边为value
					key=dataStr.substring(0,dataStr.indexOf("=")).trim();
					value=dataStr.substring(dataStr.indexOf("=")+1);
						
					
				}else{
					if (StringUtil.isNullOrEmpty(dataStr))
						continue;
					if (dataStr.equals("\n"))
						continue;
					value += "\n";
					value += dataStr;
				}
				
			}
			
			put (key, value);
			key = null;
			value = null;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fis.close();
			} catch (IOException e) { 
				e.printStackTrace();
			}
		}
	}
	
	
	private void put(String key, String value) {
		if (key!=null){
			System.out.println(key + "=" + value);
			
			value = value.trim();
			
			if (key.contains(".")){
				List<String> keyList = KeyUtil.getKeyList(key);
				int size = keyList.size();

				Map<String, Object> mapObject = map;
				int length = size - 1;
				for (int i = 0; i < length; i++) {
					String k = keyList.get(i);
					Object o = mapObject.get(k);
					if (o == null){
						o = new ConcurrentHashMap<String,Object>();
						mapObject.put(k, o);
					}
					if (mapObject instanceof Map) {
                        mapObject = (Map<String, Object>) o;
                    }else{
					    System.err.println("_________Config unhappy for x7-config's map:  key = " + k);
                    }
				}
				if (mapObject instanceof Map) {
                    mapObject.put(keyList.get(length), value);
                }else{
                    System.err.println("_________Config unhappy for x7-config's map:  key = " + keyList.get(length));
                }
			}else {
				map.put(key, value);
			}
		}
		
	}
	
	/**
	 * 将文件读到list中
	 * @param path
	 * @return
	 */
	public static List<String> readContent(String path){
		List<String> list=new ArrayList<String>();
		FileInputStream fis=null;
		BufferedReader br=null;
		try {
			fis=new FileInputStream(path);
			br=new BufferedReader(new InputStreamReader(fis,"utf-8"));
			String dataStr="";
			while((dataStr=br.readLine())!=null){ 
				list.add(dataStr);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fis.close();
			} catch (IOException e) { 
				e.printStackTrace();
			}
		}
		return list;
	}
	
	 
	/**
	 * 写入文件
	 * @param path
	 * @param data
	 */
	public static void writeConfig(String path,List<String> data){
		FileOutputStream fos=null;
		BufferedWriter br=null;
		try {
			fos=new FileOutputStream(path);
			br=new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
			for (String str : data) {
				br.write(str);
				br.newLine();
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}finally{
			 try {
				br.flush();
				fos.flush();
				br.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public void readConfigs(String path,String[] ativeProfiles){

		File file = new File(path);

		if (file == null)
			return;
		if (file.isDirectory()){

			for (File childFile : file.listFiles()){
				if (childFile.isDirectory()){
					readConfigs(childFile.getPath(),ativeProfiles);
				}else {

					String name = childFile.getName().toLowerCase();

					boolean ignored = true;
					for (String suffix : configFileSuffixArr) {
						if (name.endsWith(suffix)) {
							ignored = false;
							break;
						}
					}

					if (ignored)
						continue;

					if (ativeProfiles == null || ativeProfiles.length==0){
						System.out.println("\n[" +  name+ "]");
						readConfig(childFile.getPath());
					}else {
						if (name.endsWith("application.properties"))
							continue;
						if (!name.contains("-")){
							System.out.println("\n[" + name + "]");
							if (!(name.contains("log4j")||name.contains("logback"))) {
								readConfig(childFile.getPath());
							}
							continue;
						}

						for (String profile : ativeProfiles) {
							if (name.contains("-" + profile + ".")) {
								System.out.println("\n[" + name + "]");
								readConfig(childFile.getPath());
								break;
							}
						}
					}

				}
			}

		}
	}
}
