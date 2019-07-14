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

import java.util.Arrays;

/**
 * 
 * 英文字符�?�阿拉伯数字�?分�?器
 */
class LetterSegmenter implements ISegmenter {
	
	//�?分�?器标签
	static final String SEGMENTER_NAME = "LETTER_SEGMENTER";
	//链接符�?�
	private static final char[] Letter_Connector = new char[]{'#' , '&' , '+' , '-' , '.' , '@' , '_'};
	
	//数字符�?�
	private static final char[] Num_Connector = new char[]{',' , '.'};
	
	/*
	 * �?元的开始�?置，
	 * �?�时作为�?分�?器状�?标识
	 * 当start > -1 时，标识当�?的分�?器正在处�?�字符
	 */
	private int start;
	/*
	 * 记录�?元结�?��?置
	 * end记录的是在�?元中最�?�一个出现的Letter但�?�Sign_Connector的字符的�?置
	 */
	private int end;
	
	/*
	 * 字�?起始�?置
	 */
	private int englishStart;

	/*
	 * 字�?结�?��?置
	 */
	private int englishEnd;
	
	/*
	 * 阿拉伯数字起始�?置
	 */
	private int arabicStart;
	
	/*
	 * 阿拉伯数字结�?��?置
	 */
	private int arabicEnd;
	
	LetterSegmenter(){
		Arrays.sort(Letter_Connector);
		Arrays.sort(Num_Connector);
		this.start = -1;
		this.end = -1;
		this.englishStart = -1;
		this.englishEnd = -1;
		this.arabicStart = -1;
		this.arabicEnd = -1;
	}


	/* (non-Javadoc)
	 * @see org.wltea.analyzer.core.ISegmenter#analyze(org.wltea.analyzer.core.AnalyzeContext)
	 */
	public void analyze(AnalyzeContext context) {
		boolean bufferLockFlag = false;
		//处�?�英文字�?
		bufferLockFlag = this.processEnglishLetter(context) || bufferLockFlag;
		//处�?�阿拉伯字�?
		bufferLockFlag = this.processArabicLetter(context) || bufferLockFlag;
		//处�?�混�?�字�?(这个�?放最�?�处�?�，�?�以通过QuickSortSet排除�?�?)
		bufferLockFlag = this.processMixLetter(context) || bufferLockFlag;
		
		//判断是�?��?定缓冲区
		if(bufferLockFlag){
			context.lockBuffer(SEGMENTER_NAME);
		}else{
			//对缓冲区解�?
			context.unlockBuffer(SEGMENTER_NAME);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.wltea.analyzer.core.ISegmenter#reset()
	 */
	public void reset() {
		this.start = -1;
		this.end = -1;
		this.englishStart = -1;
		this.englishEnd = -1;
		this.arabicStart = -1;
		this.arabicEnd = -1;
	}	
	
	/**
	 * 处�?�数字字�?混�?�输出
	 * 如：windos2000 | linliangyi2005@gmail.com
//	 * @param input
	 * @param context
	 * @return
	 */
	private boolean processMixLetter(AnalyzeContext context){
		boolean needLock = false;
		
		if(this.start == -1){//当�?的分�?器尚未开始处�?�字符
			if(CharacterUtil.CHAR_ARABIC == context.getCurrentCharType()
					|| CharacterUtil.CHAR_ENGLISH == context.getCurrentCharType()){
				//记录起始指针的�?置,标明分�?器进入处�?�状�?
				this.start = context.getCursor();
				this.end = start;
			}
			
		}else{//当�?的分�?器正在处�?�字符			
			if(CharacterUtil.CHAR_ARABIC == context.getCurrentCharType()
					|| CharacterUtil.CHAR_ENGLISH == context.getCurrentCharType()){
				//记录下�?�能的结�?��?置
				this.end = context.getCursor();
				
			}else if(CharacterUtil.CHAR_USELESS == context.getCurrentCharType()
						&& this.isLetterConnector(context.getCurrentChar())){
				//记录下�?�能的结�?��?置
				this.end = context.getCursor();
			}else{
				//�?�到�?�Letter字符，输出�?元
				Lexeme newLexeme = new Lexeme(context.getBufferOffset() , this.start , this.end - this.start + 1 , Lexeme.TYPE_LETTER);
				context.addLexeme(newLexeme);
				this.start = -1;
				this.end = -1;
			}			
		}
		
		//判断缓冲区是�?�已�?读完
		if(context.isBufferConsumed() && (this.start != -1 && this.end != -1)){
            //缓冲以读完，输出�?元
            Lexeme newLexeme = new Lexeme(context.getBufferOffset() , this.start , this.end - this.start + 1 , Lexeme.TYPE_LETTER);
            context.addLexeme(newLexeme);
            this.start = -1;
            this.end = -1;
		}
		
		//判断是�?��?定缓冲区
		if(this.start == -1 && this.end == -1){
			//对缓冲区解�?
			needLock = false;
		}else{
			needLock = true;
		}
		return needLock;
	}
	
	/**
	 * 处�?�纯英文字�?输出
	 * @param context
	 * @return
	 */
	private boolean processEnglishLetter(AnalyzeContext context){
		boolean needLock = false;
		
		if(this.englishStart == -1){//当�?的分�?器尚未开始处�?�英文字符	
			if(CharacterUtil.CHAR_ENGLISH == context.getCurrentCharType()){
				//记录起始指针的�?置,标明分�?器进入处�?�状�?
				this.englishStart = context.getCursor();
				this.englishEnd = this.englishStart;
			}
		}else {//当�?的分�?器正在处�?�英文字符	
			if(CharacterUtil.CHAR_ENGLISH == context.getCurrentCharType()){
				//记录当�?指针�?置为结�?��?置
				this.englishEnd =  context.getCursor();
			}else{
				//�?�到�?�English字符,输出�?元
				Lexeme newLexeme = new Lexeme(context.getBufferOffset() , this.englishStart , this.englishEnd - this.englishStart + 1 , Lexeme.TYPE_ENGLISH);
				context.addLexeme(newLexeme);
				this.englishStart = -1;
				this.englishEnd= -1;
			}
		}
		
		//判断缓冲区是�?�已�?读完
		if(context.isBufferConsumed() && (this.englishStart != -1 && this.englishEnd != -1)){
            //缓冲以读完，输出�?元
            Lexeme newLexeme = new Lexeme(context.getBufferOffset() , this.englishStart , this.englishEnd - this.englishStart + 1 , Lexeme.TYPE_ENGLISH);
            context.addLexeme(newLexeme);
            this.englishStart = -1;
            this.englishEnd= -1;
		}	
		
		//判断是�?��?定缓冲区
		if(this.englishStart == -1 && this.englishEnd == -1){
			//对缓冲区解�?
			needLock = false;
		}else{
			needLock = true;
		}
		return needLock;			
	}
	
	/**
	 * 处�?�阿拉伯数字输出
	 * @param context
	 * @return
	 */
	private boolean processArabicLetter(AnalyzeContext context){
		boolean needLock = false;
		
		if(this.arabicStart == -1){//当�?的分�?器尚未开始处�?�数字字符	
			if(CharacterUtil.CHAR_ARABIC == context.getCurrentCharType()){
				//记录起始指针的�?置,标明分�?器进入处�?�状�?
				this.arabicStart = context.getCursor();
				this.arabicEnd = this.arabicStart;
			}
		}else {//当�?的分�?器正在处�?�数字字符	
			if(CharacterUtil.CHAR_ARABIC == context.getCurrentCharType()){
				//记录当�?指针�?置为结�?��?置
				this.arabicEnd = context.getCursor();
			}else if(CharacterUtil.CHAR_USELESS == context.getCurrentCharType()
					&& this.isNumConnector(context.getCurrentChar())){
				//�?输出数字，但�?标记结�?�
			}else{
				////�?�到�?�Arabic字符,输出�?元
				Lexeme newLexeme = new Lexeme(context.getBufferOffset() , this.arabicStart , this.arabicEnd - this.arabicStart + 1 , Lexeme.TYPE_ARABIC);
				context.addLexeme(newLexeme);
				this.arabicStart = -1;
				this.arabicEnd = -1;
			}
		}
		
		//判断缓冲区是�?�已�?读完
		if(context.isBufferConsumed() && (this.arabicStart != -1 && this.arabicEnd != -1)){
            //生�?已切分的�?元
            Lexeme newLexeme = new Lexeme(context.getBufferOffset() ,  this.arabicStart , this.arabicEnd - this.arabicStart + 1 , Lexeme.TYPE_ARABIC);
            context.addLexeme(newLexeme);
            this.arabicStart = -1;
            this.arabicEnd = -1;
		}
		
		//判断是�?��?定缓冲区
		if(this.arabicStart == -1 && this.arabicEnd == -1){
			//对缓冲区解�?
			needLock = false;
		}else{
			needLock = true;
		}
		return needLock;		
	}	

	/**
	 * 判断是�?�是字�?连接符�?�
	 * @param input
	 * @return
	 */
	private boolean isLetterConnector(char input){
		int index = Arrays.binarySearch(Letter_Connector, input);
		return index >= 0;
	}
	
	/**
	 * 判断是�?�是数字连接符�?�
	 * @param input
	 * @return
	 */
	private boolean isNumConnector(char input){
		int index = Arrays.binarySearch(Num_Connector, input);
		return index >= 0;
	}
}
