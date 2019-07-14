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

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.dic.Dictionary;

/**
 * 
 * 分�?器上下文状�?
 * 
 */
class AnalyzeContext {
	
	//默认缓冲区大�?
	private static final int BUFF_SIZE = 4096;
	//缓冲区耗尽的临界值
	private static final int BUFF_EXHAUST_CRITICAL = 100;	
	
 
	//字符串读�?�缓冲
    private char[] segmentBuff;
    //字符类型数组
    private int[] charTypes;
    
    
    //记录Reader内已分�?的字串总长度
    //在分多段分�?�?元时，该�?��?累计当�?的segmentBuff相对于reader起始�?置的�?移
	private int buffOffset;	
    //当�?缓冲区�?置指针
    private int cursor;
    //最近一次读入的,�?�处�?�的字串长度
	private int available;

	
	//�?分�?器�?
    //该集�?��?�空，说明有�?分�?器在�?�用segmentBuff
    private Set<String> buffLocker;
    
    //原始分�?结果集�?�，未�?歧义处�?�
    private QuickSortSet orgLexemes;    
    //LexemePath�?置索引表
    private Map<Integer , LexemePath> pathMap;    
    //最终分�?结果集
    private LinkedList<Lexeme> results;
	//分�?器�?置项
	private Configuration cfg;

    public AnalyzeContext(Configuration configuration){
        this.cfg = configuration;
    	this.segmentBuff = new char[BUFF_SIZE];
    	this.charTypes = new int[BUFF_SIZE];
    	this.buffLocker = new HashSet<String>();
    	this.orgLexemes = new QuickSortSet();
    	this.pathMap = new HashMap<Integer , LexemePath>();    	
    	this.results = new LinkedList<Lexeme>();
    }
    
    int getCursor(){
    	return this.cursor;
    }
    
    char[] getSegmentBuff(){
    	return this.segmentBuff;
    }
    
    char getCurrentChar(){
    	return this.segmentBuff[this.cursor];
    }
    
    int getCurrentCharType(){
    	return this.charTypes[this.cursor];
    }
    
    int getBufferOffset(){
    	return this.buffOffset;
    }
	
    /**
     * 根�?�context的上下文情况，填充segmentBuff 
     * @param reader
     * @return 返回待分�?的（有效的）字串长度
     * @throws java.io.IOException
     */
    int fillBuffer(Reader reader) throws IOException{
    	int readCount = 0;
    	if(this.buffOffset == 0){
    		//首次读�?�reader
    		readCount = reader.read(segmentBuff);
    	}else{
    		int offset = this.available - this.cursor;
    		if(offset > 0){
    			//最近一次读�?�的>最近一次处�?�的，将未处�?�的字串拷�?到segmentBuff头部
    			System.arraycopy(this.segmentBuff , this.cursor , this.segmentBuff , 0 , offset);
    			readCount = offset;
    		}
    		//继续读�?�reader ，以onceReadIn - onceAnalyzed为起始�?置，继续填充segmentBuff剩余的部分
    		readCount += reader.read(this.segmentBuff , offset , BUFF_SIZE - offset);
    	}            	
    	//记录最�?�一次从Reader中读入的�?�用字符长度
    	this.available = readCount;
    	//�?置当�?指针
    	this.cursor = 0;
    	return readCount;
    }

    /**
     * �?始化buff指针，处�?�第一个字符
     */
    void initCursor(){
    	this.cursor = 0;
    	this.segmentBuff[this.cursor] = CharacterUtil.regularize(this.segmentBuff[this.cursor],cfg.isEnableLowercase());
    	this.charTypes[this.cursor] = CharacterUtil.identifyCharType(this.segmentBuff[this.cursor]);
    }
    
    /**
     * 指针+1
     * �?功返回 true； 指针已�?到了buff尾部，�?能�?进，返回false
     * 并处�?�当�?字符
     */
    boolean moveCursor(){
    	if(this.cursor < this.available - 1){
    		this.cursor++;
        	this.segmentBuff[this.cursor] = CharacterUtil.regularize(this.segmentBuff[this.cursor],cfg.isEnableLowercase());
        	this.charTypes[this.cursor] = CharacterUtil.identifyCharType(this.segmentBuff[this.cursor]);
    		return true;
    	}else{
    		return false;
    	}
    }
	
    /**
     * 设置当�?segmentBuff为�?定状�?
     * 加入�?�用segmentBuff的�?分�?器�??称，表示�?�用segmentBuff
     * @param segmenterName
     */
	void lockBuffer(String segmenterName){
		this.buffLocker.add(segmenterName);
	}
	
	/**
	 * 移除指定的�?分�?器�??，释放对segmentBuff的�?�用
	 * @param segmenterName
	 */
	void unlockBuffer(String segmenterName){
		this.buffLocker.remove(segmenterName);
	}
	
	/**
	 * �?��?buffLocker中存在segmenterName
	 * 则buffer被�?定
	 * @return boolean 缓冲去是�?�被�?定
	 */
	boolean isBufferLocked(){
		return this.buffLocker.size() > 0;
	}

	/**
	 * 判断当�?segmentBuff是�?�已�?用完
	 * 当�?执针cursor移至segmentBuff末端this.available - 1
	 * @return
	 */
	boolean isBufferConsumed(){
		return this.cursor == this.available - 1;
	}
	
	/**
	 * 判断segmentBuff是�?�需�?读�?�新数�?�
	 * 
	 * 满足一下�?�件时，
	 * 1.available == BUFF_SIZE 表示buffer满载
	 * 2.buffIndex < available - 1 && buffIndex > available - BUFF_EXHAUST_CRITICAL表示当�?指针处于临界区内
	 * 3.!context.isBufferLocked()表示没有segmenter在�?�用buffer
	 * �?中断当�?循环（buffer�?进行移�?，并�?读�?�数�?�的�?作）
	 * @return
	 */
	boolean needRefillBuffer(){
		return this.available == BUFF_SIZE 
			&& this.cursor < this.available - 1   
			&& this.cursor  > this.available - BUFF_EXHAUST_CRITICAL
			&& !this.isBufferLocked();
	}
	
	/**
	 * 累计当�?的segmentBuff相对于reader起始�?置的�?移
	 */
	void markBufferOffset(){
		this.buffOffset += this.cursor;
	}
	
	/**
	 * �?�分�?结果集添加�?元
	 * @param lexeme
	 */
	void addLexeme(Lexeme lexeme){
		this.orgLexemes.addLexeme(lexeme);
	}
	
	/**
	 * 添加分�?结果路径
	 * 路径起始�?置 ---> 路径 映射表
	 * @param path
	 */
	void addLexemePath(LexemePath path){
		if(path != null){
			this.pathMap.put(path.getPathBegin(), path);
		}
	}
	
	
	/**
	 * 返回原始分�?结果
	 * @return
	 */
	QuickSortSet getOrgLexemes(){
		return this.orgLexemes;
	}
	
	/**
	 * 推�?分�?结果到结果集�?�
	 * 1.从buff头部�??历到this.cursor已处�?��?置
	 * 2.将map中存在的分�?结果推入results
	 * 3.将map中�?存在的CJDK字符以�?�字方�?推入results
	 */
	void outputToResult(){
		int index = 0;
		for( ; index <= this.cursor ;){
			//跳过�?�CJK字符
			if(CharacterUtil.CHAR_USELESS == this.charTypes[index]){
				index++;
				continue;
			}
			//从pathMap找出对应index�?置的LexemePath
			LexemePath path = this.pathMap.get(index);
			if(path != null){
				//输出LexemePath中的lexeme到results集�?�
				Lexeme l = path.pollFirst();
				while(l != null){
					this.results.add(l);
					//字典中无�?�字，但是�?元冲�?了，切分出相交�?元的�?一个�?元中的�?�字
					/*int innerIndex = index + 1;
					for (; innerIndex < index + l.getLength(); innerIndex++) {
						Lexeme innerL = path.peekFirst();
						if (innerL != null && innerIndex == innerL.getBegin()) {
							this.outputSingleCJK(innerIndex - 1);
						}
					}*/
					
					//将index移至lexeme�?�
					index = l.getBegin() + l.getLength();					
					l = path.pollFirst();
					if(l != null){
						//输出path内部，�?元间�?��?的�?�字
						for(;index < l.getBegin();index++){
							this.outputSingleCJK(index);
						}
					}
				}
			}else{//pathMap中找�?到index对应的LexemePath
				//�?�字输出
				this.outputSingleCJK(index);
				index++;
			}
		}
		//清空当�?的Map
		this.pathMap.clear();
	}
	
	/**
	 * 对CJK字符进行�?�字输出
	 * @param index
	 */
	private void outputSingleCJK(int index){
		if(CharacterUtil.CHAR_CHINESE == this.charTypes[index]){			
			Lexeme singleCharLexeme = new Lexeme(this.buffOffset , index , 1 , Lexeme.TYPE_CNCHAR);
			this.results.add(singleCharLexeme);
		}else if(CharacterUtil.CHAR_OTHER_CJK == this.charTypes[index]){
			Lexeme singleCharLexeme = new Lexeme(this.buffOffset , index , 1 , Lexeme.TYPE_OTHER_CJK);
			this.results.add(singleCharLexeme);
		}
	}
		
	/**
	 * 返回lexeme 
	 * 
	 * �?�时处�?��?�并
	 * @return
	 */
	Lexeme getNextLexeme(){
		//从结果集�?�出，并移除第一个Lexme
		Lexeme result = this.results.pollFirst();
		while(result != null){
    		//数�?�?�?�并
    		this.compound(result);
    		if(Dictionary.getSingleton().isStopWord(this.segmentBuff ,  result.getBegin() , result.getLength())){
       			//是�?�止�?继续�?�列表的下一个
    			result = this.results.pollFirst(); 				
    		}else{
	 			//�?是�?�止�?, 生�?lexeme的�?元文本,输出
	    		result.setLexemeText(String.valueOf(segmentBuff , result.getBegin() , result.getLength()));
	    		break;
    		}
		}
		return result;
	}
	
	/**
	 * �?置分�?上下文状�?
	 */
	void reset(){		
		this.buffLocker.clear();
        this.orgLexemes = new QuickSortSet();
        this.available =0;
        this.buffOffset = 0;
    	this.charTypes = new int[BUFF_SIZE];
    	this.cursor = 0;
    	this.results.clear();
    	this.segmentBuff = new char[BUFF_SIZE];
    	this.pathMap.clear();
	}
	
	/**
	 * 组�?��?元
	 */
	private void compound(Lexeme result){

		if(!this.cfg.isUseSmart()){
			return ;
		}
   		//数�?�?�?�并处�?�
		if(!this.results.isEmpty()){

			if(Lexeme.TYPE_ARABIC == result.getLexemeType()){
				Lexeme nextLexeme = this.results.peekFirst();
				boolean appendOk = false;
				if(Lexeme.TYPE_CNUM == nextLexeme.getLexemeType()){
					//�?�并英文数�?+中文数�?
					appendOk = result.append(nextLexeme, Lexeme.TYPE_CNUM);
				}else if(Lexeme.TYPE_COUNT == nextLexeme.getLexemeType()){
					//�?�并英文数�?+中文�?�?
					appendOk = result.append(nextLexeme, Lexeme.TYPE_CQUAN);
				}
				if(appendOk){
					//弹出
					this.results.pollFirst(); 
				}
			}
			
			//�?�能存在第二轮�?�并
			if(Lexeme.TYPE_CNUM == result.getLexemeType() && !this.results.isEmpty()){
				Lexeme nextLexeme = this.results.peekFirst();
				boolean appendOk = false;
				 if(Lexeme.TYPE_COUNT == nextLexeme.getLexemeType()){
					 //�?�并中文数�?+中文�?�?
 					appendOk = result.append(nextLexeme, Lexeme.TYPE_CQUAN);
 				}  
				if(appendOk){
					//弹出
					this.results.pollFirst();   				
				}
			}

		}
	}
	
}
