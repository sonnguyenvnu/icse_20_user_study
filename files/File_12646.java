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

/**
 * 表示一次�?典匹�?的命中
 */
public class Hit {
	//Hit�?匹�?
	private static final int UNMATCH = 0x00000000;
	//Hit完全匹�?
	private static final int MATCH = 0x00000001;
	//Hit�?缀匹�?
	private static final int PREFIX = 0x00000010;
	
	
	//该HIT当�?状�?，默认未匹�?
	private int hitState = UNMATCH;
	
	//记录�?典匹�?过程中，当�?匹�?到的�?典分支节点
	private DictSegment matchedDictSegment; 
	/*
	 * �?段开始�?置
	 */
	private int begin;
	/*
	 * �?段的结�?��?置
	 */
	private int end;
	
	
	/**
	 * 判断是�?�完全匹�?
	 */
	public boolean isMatch() {
		return (this.hitState & MATCH) > 0;
	}
	/**
	 * 
	 */
	public void setMatch() {
		this.hitState = this.hitState | MATCH;
	}

	/**
	 * 判断是�?�是�?的�?缀
	 */
	public boolean isPrefix() {
		return (this.hitState & PREFIX) > 0;
	}
	/**
	 * 
	 */
	public void setPrefix() {
		this.hitState = this.hitState | PREFIX;
	}
	/**
	 * 判断是�?�是�?匹�?
	 */
	public boolean isUnmatch() {
		return this.hitState == UNMATCH ;
	}
	/**
	 * 
	 */
	public void setUnmatch() {
		this.hitState = UNMATCH;
	}
	
	public DictSegment getMatchedDictSegment() {
		return matchedDictSegment;
	}
	
	public void setMatchedDictSegment(DictSegment matchedDictSegment) {
		this.matchedDictSegment = matchedDictSegment;
	}
	
	public int getBegin() {
		return begin;
	}
	
	public void setBegin(int begin) {
		this.begin = begin;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}	
	
}
