/**
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
package org.wltea.analyzer.core;


/**
 * Lexeme链（路径）
 */
class LexemePath extends QuickSortSet implements Comparable<LexemePath>{
	
	//起始�?置
	private int pathBegin;
	//结�?�
	private int pathEnd;
	//�?元链的有效字符长度
	private int payloadLength;
	
	LexemePath(){
		this.pathBegin = -1;
		this.pathEnd = -1;
		this.payloadLength = 0;
	}

	/**
	 * �?�LexemePath追加相交的Lexeme
	 * @param lexeme
	 * @return 
	 */
	boolean addCrossLexeme(Lexeme lexeme){
		if(this.isEmpty()){
			this.addLexeme(lexeme);
			this.pathBegin = lexeme.getBegin();
			this.pathEnd = lexeme.getBegin() + lexeme.getLength();
			this.payloadLength += lexeme.getLength();
			return true;
			
		}else if(this.checkCross(lexeme)){
			this.addLexeme(lexeme);
			if(lexeme.getBegin() + lexeme.getLength() > this.pathEnd){
				this.pathEnd = lexeme.getBegin() + lexeme.getLength();
			}
			this.payloadLength = this.pathEnd - this.pathBegin;
			return true;
			
		}else{
			return  false;
			
		}
	}
	
	/**
	 * �?�LexemePath追加�?相交的Lexeme
	 * @param lexeme
	 * @return 
	 */
	boolean addNotCrossLexeme(Lexeme lexeme){
		if(this.isEmpty()){
			this.addLexeme(lexeme);
			this.pathBegin = lexeme.getBegin();
			this.pathEnd = lexeme.getBegin() + lexeme.getLength();
			this.payloadLength += lexeme.getLength();
			return true;
			
		}else if(this.checkCross(lexeme)){
			return  false;
			
		}else{
			this.addLexeme(lexeme);
			this.payloadLength += lexeme.getLength();
			Lexeme head = this.peekFirst();
			this.pathBegin = head.getBegin();
			Lexeme tail = this.peekLast();
			this.pathEnd = tail.getBegin() + tail.getLength();
			return true;
			
		}
	}
	
	/**
	 * 移除尾部的Lexeme
	 * @return
	 */
	Lexeme removeTail(){
		Lexeme tail = this.pollLast();
		if(this.isEmpty()){
			this.pathBegin = -1;
			this.pathEnd = -1;
			this.payloadLength = 0;			
		}else{		
			this.payloadLength -= tail.getLength();
			Lexeme newTail = this.peekLast();
			this.pathEnd = newTail.getBegin() + newTail.getLength();
		}
		return tail;
	}
	
	/**
	 * 检测�?元�?置交�?�（有歧义的切分）
	 * @param lexeme
	 * @return
	 */
	boolean checkCross(Lexeme lexeme){
		return (lexeme.getBegin() >= this.pathBegin && lexeme.getBegin() < this.pathEnd)
				|| (this.pathBegin >= lexeme.getBegin() && this.pathBegin < lexeme.getBegin()+ lexeme.getLength());
	}
	
	int getPathBegin() {
		return pathBegin;
	}

	int getPathEnd() {
		return pathEnd;
	}

	/**
	 * 获�?�Path的有效�?长
	 * @return
	 */
	int getPayloadLength(){
		return this.payloadLength;
	}
	
	/**
	 * 获�?�LexemePath的路径长度
	 * @return
	 */
	int getPathLength(){
		return this.pathEnd - this.pathBegin;
	}
	

	/**
	 * X�?��?（�?元长度积）
	 * @return
	 */
	int getXWeight(){
		int product = 1;
		Cell c = this.getHead();
		while( c != null && c.getLexeme() != null){
			product *= c.getLexeme().getLength();
			c = c.getNext();
		}
		return product;
	}
	
	/**
	 * �?元�?置�?��?
	 * @return
	 */
	int getPWeight(){
		int pWeight = 0;
		int p = 0;
		Cell c = this.getHead();
		while( c != null && c.getLexeme() != null){
			p++;
			pWeight += p * c.getLexeme().getLength() ;
			c = c.getNext();
		}
		return pWeight;		
	}
	
	LexemePath copy(){
		LexemePath theCopy = new LexemePath();
		theCopy.pathBegin = this.pathBegin;
		theCopy.pathEnd = this.pathEnd;
		theCopy.payloadLength = this.payloadLength;
		Cell c = this.getHead();
		while( c != null && c.getLexeme() != null){
			theCopy.addLexeme(c.getLexeme());
			c = c.getNext();
		}
		return theCopy;
	}

	public int compareTo(LexemePath o) {
		//比较有效文本长度
		if(this.payloadLength > o.payloadLength){
			return -1;
		}else if(this.payloadLength < o.payloadLength){
			return 1;
		}else{
			//比较�?元个数，越少越好
			if(this.size() < o.size()){
				return -1;
			}else if (this.size() > o.size()){
				return 1;
			}else{
				//路径跨度越大越好
				if(this.getPathLength() >  o.getPathLength()){
					return -1;
				}else if(this.getPathLength() <  o.getPathLength()){
					return 1;
				}else {
					//根�?�统计学结论，逆�?�切分概率高于正�?�切分，因此�?置越�?��?�的优先
					if(this.pathEnd > o.pathEnd){
						return -1;
					}else if(pathEnd < o.pathEnd){
						return 1;
					}else{
						//�?长越平�?�越好
						if(this.getXWeight() > o.getXWeight()){
							return -1;
						}else if(this.getXWeight() < o.getXWeight()){
							return 1;
						}else {
							//�?元�?置�?��?比较
							if(this.getPWeight() > o.getPWeight()){
								return -1;
							}else if(this.getPWeight() < o.getPWeight()){
								return 1;
							}
							
						}
					}
				}
			}
		}
		return 0;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("pathBegin  : ").append(pathBegin).append("\r\n");
		sb.append("pathEnd  : ").append(pathEnd).append("\r\n");
		sb.append("payloadLength  : ").append(payloadLength).append("\r\n");
		Cell head = this.getHead();
		while(head != null){
			sb.append("lexeme : ").append(head.getLexeme()).append("\r\n");
			head = head.getNext();
		}
		return sb.toString();
	}

}
