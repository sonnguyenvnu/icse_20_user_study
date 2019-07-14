/**
 * 
 * IK 中文分�?  版本 5.0
 * IK Analyzer release 5.0
 * 
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
 *
 * �?代�?由林良益(linliangyi2005@gmail.com)�??供
 * 版�?�声明 2012，乌龙茶工作室
 * provided by Linliangyi and copyright 2012 by Oolong studio
 * 
 */
package org.wltea.analyzer.dic;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * �?典树分段，表示�?典树的一个分�?
 */
class DictSegment implements Comparable<DictSegment>{
	
	//公用字典表，存储汉字
	private static final Map<Character , Character> charMap = new ConcurrentHashMap<Character , Character>(16 , 0.95f);
	//数组大�?上�?
	private static final int ARRAY_LENGTH_LIMIT = 3;

	
	//Map存储结构
	private Map<Character , DictSegment> childrenMap;
	//数组方�?存储结构
	private DictSegment[] childrenArray;
	
	
	//当�?节点上存储的字符
	private Character nodeChar;
	//当�?节点存储的Segment数目
	//storeSize <=ARRAY_LENGTH_LIMIT ，使用数组存储， storeSize >ARRAY_LENGTH_LIMIT ,则使用Map存储
	private int storeSize = 0;
	//当�?DictSegment状�? ,默认 0 , 1表示从根节点到当�?节点的路径表示一个�?
	private int nodeState = 0;	
	
	
	DictSegment(Character nodeChar){
		if(nodeChar == null){
			throw new IllegalArgumentException("�?�数为空异常，字符�?能为空");
		}
		this.nodeChar = nodeChar;
	}

	Character getNodeChar() {
		return nodeChar;
	}
	
	/*
	 * 判断是�?�有下一个节点
	 */
	boolean hasNextNode(){
		return  this.storeSize > 0;
	}
	
	/**
	 * 匹�?�?段
	 * @param charArray
	 * @return Hit
	 */
	Hit match(char[] charArray){
		return this.match(charArray , 0 , charArray.length , null);
	}
	
	/**
	 * 匹�?�?段
	 * @param charArray
	 * @param begin
	 * @param length
	 * @return Hit 
	 */
	Hit match(char[] charArray , int begin , int length){
		return this.match(charArray , begin , length , null);
	}
	
	/**
	 * 匹�?�?段
	 * @param charArray
	 * @param begin
	 * @param length
	 * @param searchHit
	 * @return Hit 
	 */
	Hit match(char[] charArray , int begin , int length , Hit searchHit){
		
		if(searchHit == null){
			//如果hit为空，新建
			searchHit= new Hit();
			//设置hit的其实文本�?置
			searchHit.setBegin(begin);
		}else{
			//�?�则�?将HIT状�?�?置
			searchHit.setUnmatch();
		}
		//设置hit的当�?处�?��?置
		searchHit.setEnd(begin);

        Character keyChar = Character.valueOf(charArray[begin]);
		DictSegment ds = null;
		
		//引用实例�?��?为本地�?��?，�?��?查询时�?�到更新的�?�步问题
		DictSegment[] segmentArray = this.childrenArray;
		Map<Character , DictSegment> segmentMap = this.childrenMap;		
		
		//STEP1 在节点中查找keyChar对应的DictSegment
		if(segmentArray != null){
			//在数组中查找
			DictSegment keySegment = new DictSegment(keyChar);
			int position = Arrays.binarySearch(segmentArray, 0 , this.storeSize , keySegment);
			if(position >= 0){
				ds = segmentArray[position];
			}

		}else if(segmentMap != null){
			//在map中查找
			ds = (DictSegment)segmentMap.get(keyChar);
		}
		
		//STEP2 找到DictSegment，判断�?的匹�?状�?，是�?�继续递归，还是返回结果
		if(ds != null){			
			if(length > 1){
				//�?未匹�?完，继续往下�?�索
				return ds.match(charArray, begin + 1 , length - 1 , searchHit);
			}else if (length == 1){
				
				//�?�索最�?�一个char
				if(ds.nodeState == 1){
					//添加HIT状�?为完全匹�?
					searchHit.setMatch();
				}
				if(ds.hasNextNode()){
					//添加HIT状�?为�?缀匹�?
					searchHit.setPrefix();
					//记录当�?�?置的DictSegment
					searchHit.setMatchedDictSegment(ds);
				}
				return searchHit;
			}
			
		}
		//STEP3 没有找到DictSegment， 将HIT设置为�?匹�?
		return searchHit;		
	}

	/**
	 * 加载填充�?典片段
	 * @param charArray
	 */
	void fillSegment(char[] charArray){
		this.fillSegment(charArray, 0 , charArray.length , 1); 
	}
	
	/**
	 * �?蔽�?典中的一个�?
	 * @param charArray
	 */
	void disableSegment(char[] charArray){
		this.fillSegment(charArray, 0 , charArray.length , 0); 
	}
	
	/**
	 * 加载填充�?典片段
	 * @param charArray
	 * @param begin
	 * @param length
	 * @param enabled
	 */
	private synchronized void fillSegment(char[] charArray , int begin , int length , int enabled){
		//获�?�字典表中的汉字对象
		Character beginChar = Character.valueOf(charArray[begin]);
		Character keyChar = charMap.get(beginChar);
		//字典中没有该字，则将其添加入字典
		if(keyChar == null){
			charMap.put(beginChar, beginChar);
			keyChar = beginChar;
		}
		
		//�?�索当�?节点的存储，查询对应keyChar的keyChar，如果没有则创建
		DictSegment ds = lookforSegment(keyChar , enabled);
		if(ds != null){
			//处�?�keyChar对应的segment
			if(length > 1){
				//�?元还没有完全加入�?典树
				ds.fillSegment(charArray, begin + 1, length - 1 , enabled);
			}else if (length == 1){
				//已�?是�?元的最�?�一个char,设置当�?节点状�?为enabled，
				//enabled=1表明一个完整的�?，enabled=0表示从�?典中�?蔽当�?�?
				ds.nodeState = enabled;
			}
		}

	}
	
	/**
	 * 查找本节点下对应的keyChar的segment	 * 
	 * @param keyChar
	 * @param create  =1如果没有找到，则创建新的segment ; =0如果没有找到，�?创建，返回null
	 * @return
	 */
	private DictSegment lookforSegment(Character keyChar ,  int create){
		
		DictSegment ds = null;

		if(this.storeSize <= ARRAY_LENGTH_LIMIT){
			//获�?�数组容器，如果数组未创建则创建数组
			DictSegment[] segmentArray = getChildrenArray();			
			//�?�寻数组
			DictSegment keySegment = new DictSegment(keyChar);
			int position = Arrays.binarySearch(segmentArray, 0 , this.storeSize, keySegment);
			if(position >= 0){
				ds = segmentArray[position];
			}
		
			//�??历数组�?�没有找到对应的segment
			if(ds == null && create == 1){
				ds = keySegment;
				if(this.storeSize < ARRAY_LENGTH_LIMIT){
					//数组容�?未满，使用数组存储
					segmentArray[this.storeSize] = ds;
					//segment数目+1
					this.storeSize++;
					Arrays.sort(segmentArray , 0 , this.storeSize);
					
				}else{
					//数组容�?已满，切�?�Map存储
					//获�?�Map容器，如果Map未创建,则创建Map
					Map<Character , DictSegment> segmentMap = getChildrenMap();
					//将数组中的segment�?移到Map中
					migrate(segmentArray ,  segmentMap);
					//存储新的segment
					segmentMap.put(keyChar, ds);
					//segment数目+1 ，  必须在释放数组�?执行storeSize++ ， 确�?�?端情况下，�?会�?�到空的数组
					this.storeSize++;
					//释放当�?的数组引用
					this.childrenArray = null;
				}

			}			
			
		}else{
			//获�?�Map容器，如果Map未创建,则创建Map
			Map<Character , DictSegment> segmentMap = getChildrenMap();
			//�?�索Map
			ds = (DictSegment)segmentMap.get(keyChar);
			if(ds == null && create == 1){
				//构造新的segment
				ds = new DictSegment(keyChar);
				segmentMap.put(keyChar , ds);
				//当�?节点存储segment数目+1
				this.storeSize ++;
			}
		}

		return ds;
	}
	
	
	/**
	 * 获�?�数组容器
	 * 线程�?�步方法
	 */
	private DictSegment[] getChildrenArray(){
		synchronized(this){
			if(this.childrenArray == null){
					this.childrenArray = new DictSegment[ARRAY_LENGTH_LIMIT];
			}
		}
		return this.childrenArray;
	}
	
	/**
	 * 获�?�Map容器
	 * 线程�?�步方法
	 */	
	private Map<Character , DictSegment> getChildrenMap(){
		synchronized(this){
			if(this.childrenMap == null){
				this.childrenMap = new ConcurrentHashMap<Character, DictSegment>(ARRAY_LENGTH_LIMIT * 2,0.8f);
			}
		}
		return this.childrenMap;
	}
	
	/**
	 * 将数组中的segment�?移到Map中
	 * @param segmentArray
	 */
	private void migrate(DictSegment[] segmentArray , Map<Character , DictSegment> segmentMap){
		for(DictSegment segment : segmentArray){
			if(segment != null){
				segmentMap.put(segment.nodeChar, segment);
			}
		}
	}

	/**
	 * 实现Comparable接�?�
	 * @param o
	 * @return int
	 */
	public int compareTo(DictSegment o) {
		//对当�?节点存储的char进行比较
		return this.nodeChar.compareTo(o.nodeChar);
	}
	
}
