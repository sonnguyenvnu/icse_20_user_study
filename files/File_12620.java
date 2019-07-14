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
 * IK�?元对象 
 */
public class Lexeme implements Comparable<Lexeme>{
	//lexemeType常�?
	//未知
	public static final int TYPE_UNKNOWN = 0;
	//英文
	public static final int TYPE_ENGLISH = 1;
	//数字
	public static final int TYPE_ARABIC = 2;
	//英文数字混�?�
	public static final int TYPE_LETTER = 3;
	//中文�?元
	public static final int TYPE_CNWORD = 4;
	//中文�?�字
	public static final int TYPE_CNCHAR = 64;
	//日韩文字
	public static final int TYPE_OTHER_CJK = 8;
	//中文数�?
	public static final int TYPE_CNUM = 16;
	//中文�?�?
	public static final int TYPE_COUNT = 32;
	//中文数�?�?
	public static final int TYPE_CQUAN = 48;
	
	//�?元的起始�?移
	private int offset;
    //�?元的相对起始�?置
    private int begin;
    //�?元的长度
    private int length;
    //�?元文本
    private String lexemeText;
    //�?元类型
    private int lexemeType;
    
    
	public Lexeme(int offset , int begin , int length , int lexemeType){
		this.offset = offset;
		this.begin = begin;
		if(length < 0){
			throw new IllegalArgumentException("length < 0");
		}
		this.length = length;
		this.lexemeType = lexemeType;
	}
	
    /*
     * 判断�?元相等算法
     * 起始�?置�??移�?起始�?置�?终止�?置相�?�
     * @see java.lang.Object#equals(Object o)
     */
	public boolean equals(Object o){
		if(o == null){
			return false;
		}
		
		if(this == o){
			return true;
		}
		
		if(o instanceof Lexeme){
			Lexeme other = (Lexeme)o;
			if(this.offset == other.getOffset()
					&& this.begin == other.getBegin()
					&& this.length == other.getLength()){
				return true;			
			}else{
				return false;
			}
		}else{		
			return false;
		}
	}
	
    /*
     * �?元哈希编�?算法
     * @see java.lang.Object#hashCode()
     */
    public int hashCode(){
    	int absBegin = getBeginPosition();
    	int absEnd = getEndPosition();
    	return  (absBegin * 37) + (absEnd * 31) + ((absBegin * absEnd) % getLength()) * 11;
    }
    
    /*
     * �?元在排�?集�?�中的比较算法
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
	public int compareTo(Lexeme other) {
		//起始�?置优先
        if(this.begin < other.getBegin()){
            return -1;
        }else if(this.begin == other.getBegin()){
        	//�?元长度优先
        	if(this.length > other.getLength()){
        		return -1;
        	}else if(this.length == other.getLength()){
        		return 0;
        	}else {//this.length < other.getLength()
        		return 1;
        	}
        	
        }else{//this.begin > other.getBegin()
        	return 1;
        }
	}
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getBegin() {
		return begin;
	}
	/**
	 * 获�?��?元在文本中的起始�?置
	 * @return int
	 */
	public int getBeginPosition(){
		return offset + begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	/**
	 * 获�?��?元在文本中的结�?��?置
	 * @return int
	 */
	public int getEndPosition(){
		return offset + begin + length;
	}
	
	/**
	 * 获�?��?元的字符长度
	 * @return int
	 */
	public int getLength(){
		return this.length;
	}	
	
	public void setLength(int length) {
		if(this.length < 0){
			throw new IllegalArgumentException("length < 0");
		}
		this.length = length;
	}
	
	/**
	 * 获�?��?元的文本内容
	 * @return String
	 */
	public String getLexemeText() {
		if(lexemeText == null){
			return "";
		}
		return lexemeText;
	}

	public void setLexemeText(String lexemeText) {
		if(lexemeText == null){
			this.lexemeText = "";
			this.length = 0;
		}else{
			this.lexemeText = lexemeText;
			this.length = lexemeText.length();
		}
	}

	/**
	 * 获�?��?元类型
	 * @return int
	 */
	public int getLexemeType() {
		return lexemeType;
	}
	
	/**
	 * 获�?��?元类型标示字符串
	 * @return String
	 */
	public String getLexemeTypeString(){
		switch(lexemeType) {

		case TYPE_ENGLISH :
			return "ENGLISH";
			
		case TYPE_ARABIC :
			return "ARABIC";
			
		case TYPE_LETTER :
			return "LETTER";
			
		case TYPE_CNWORD : 
			return "CN_WORD";
			
		case TYPE_CNCHAR : 
			return "CN_CHAR";
			
		case TYPE_OTHER_CJK :
			return "OTHER_CJK";
			
		case TYPE_COUNT :
			return "COUNT";
			
		case TYPE_CNUM :
			return "TYPE_CNUM";
			
		case TYPE_CQUAN:	
			return "TYPE_CQUAN";
			
		default :
			return "UNKONW";
		}
	}
		

	public void setLexemeType(int lexemeType) {
		this.lexemeType = lexemeType;
	}
	
	/**
	 * �?�并两个相邻的�?元
	 * @param l
	 * @param lexemeType
	 * @return boolean �?元是�?��?功�?�并
	 */
	public boolean append(Lexeme l , int lexemeType){
		if(l != null && this.getEndPosition() == l.getBeginPosition()){
			this.length += l.getLength();
			this.lexemeType = lexemeType;
			return true;
		}else {
			return false;
		}
	}
	

	/**
	 * 
	 */
	public String toString(){
		StringBuffer strbuf = new StringBuffer();
		strbuf.append(this.getBeginPosition()).append("-").append(this.getEndPosition());
		strbuf.append(" : ").append(this.lexemeText).append(" : \t");
		strbuf.append(this.getLexemeTypeString());
		return strbuf.toString();
	}
	

}
