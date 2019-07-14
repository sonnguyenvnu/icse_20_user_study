/**
 * IK ä¸­æ–‡åˆ†è¯?  ç‰ˆæœ¬ 5.0
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
 * æº?ä»£ç ?ç”±æž—è‰¯ç›Š(linliangyi2005@gmail.com)æ??ä¾›
 * ç‰ˆæ?ƒå£°æ˜Ž 2012ï¼Œä¹Œé¾™èŒ¶å·¥ä½œå®¤
 * provided by Linliangyi and copyright 2012 by Oolong studio
 * 
 */
package org.wltea.analyzer.core;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.dic.Hit;

/**
 * 
 * ä¸­æ–‡æ•°é‡?è¯?å­?åˆ†è¯?å™¨
 */
class CN_QuantifierSegmenter implements ISegmenter{
	
	//å­?åˆ†è¯?å™¨æ ‡ç­¾
	static final String SEGMENTER_NAME = "QUAN_SEGMENTER";
	
	//ä¸­æ–‡æ•°è¯?
	private static String Chn_Num = "ä¸€äºŒä¸¤ä¸‰å››äº”å…­ä¸ƒå…«ä¹?å??é›¶å£¹è´°å??è‚†ä¼?é™†æŸ’æ?ŒçŽ–æ‹¾ç™¾å?ƒä¸‡äº¿æ‹¾ä½°ä»Ÿè?¬å„„å…†å?…å»¿";//Cnum
	private static Set<Character> ChnNumberChars = new HashSet<Character>();
	static{
		char[] ca = Chn_Num.toCharArray();
		for(char nChar : ca){
			ChnNumberChars.add(nChar);
		}
	}
	
	/*
	 * è¯?å…ƒçš„å¼€å§‹ä½?ç½®ï¼Œ
	 * å?Œæ—¶ä½œä¸ºå­?åˆ†è¯?å™¨çŠ¶æ€?æ ‡è¯†
	 * å½“start > -1 æ—¶ï¼Œæ ‡è¯†å½“å‰?çš„åˆ†è¯?å™¨æ­£åœ¨å¤„ç?†å­—ç¬¦
	 */
	private int nStart;
	/*
	 * è®°å½•è¯?å…ƒç»“æ?Ÿä½?ç½®
	 * endè®°å½•çš„æ˜¯åœ¨è¯?å…ƒä¸­æœ€å?Žä¸€ä¸ªå‡ºçŽ°çš„å?ˆç?†çš„æ•°è¯?ç»“æ?Ÿ
	 */
	private int nEnd;

	//å¾…å¤„ç?†çš„é‡?è¯?hité˜Ÿåˆ—
	private List<Hit> countHits;
	
	
	CN_QuantifierSegmenter(){
		nStart = -1;
		nEnd = -1;
		this.countHits  = new LinkedList<Hit>();
	}
	
	/**
	 * åˆ†è¯?
	 */
	public void analyze(AnalyzeContext context) {
		//å¤„ç?†ä¸­æ–‡æ•°è¯?
		this.processCNumber(context);
		//å¤„ç?†ä¸­æ–‡é‡?è¯?
		this.processCount(context);
		
		//åˆ¤æ–­æ˜¯å?¦é”?å®šç¼“å†²åŒº
		if(this.nStart == -1 && this.nEnd == -1	&& countHits.isEmpty()){
			//å¯¹ç¼“å†²åŒºè§£é”?
			context.unlockBuffer(SEGMENTER_NAME);
		}else{
			context.lockBuffer(SEGMENTER_NAME);
		}
	}
	

	/**
	 * é‡?ç½®å­?åˆ†è¯?å™¨çŠ¶æ€?
	 */
	public void reset() {
		nStart = -1;
		nEnd = -1;
		countHits.clear();
	}
	
	/**
	 * å¤„ç?†æ•°è¯?
	 */
	private void processCNumber(AnalyzeContext context){
		if(nStart == -1 && nEnd == -1){//åˆ?å§‹çŠ¶æ€?
			if(CharacterUtil.CHAR_CHINESE == context.getCurrentCharType() 
					&& ChnNumberChars.contains(context.getCurrentChar())){
				//è®°å½•æ•°è¯?çš„èµ·å§‹ã€?ç»“æ?Ÿä½?ç½®
				nStart = context.getCursor();
				nEnd = context.getCursor();
			}
		}else{//æ­£åœ¨å¤„ç?†çŠ¶æ€?
			if(CharacterUtil.CHAR_CHINESE == context.getCurrentCharType() 
					&& ChnNumberChars.contains(context.getCurrentChar())){
				//è®°å½•æ•°è¯?çš„ç»“æ?Ÿä½?ç½®
				nEnd = context.getCursor();
			}else{
				//è¾“å‡ºæ•°è¯?
				this.outputNumLexeme(context);
				//é‡?ç½®å¤´å°¾æŒ‡é’ˆ
				nStart = -1;
				nEnd = -1;
			}
		}
		
		//ç¼“å†²åŒºå·²ç»?ç”¨å®Œï¼Œè¿˜æœ‰å°šæœªè¾“å‡ºçš„æ•°è¯?
		if(context.isBufferConsumed() && (nStart != -1 && nEnd != -1)){
			//è¾“å‡ºæ•°è¯?
			outputNumLexeme(context);
			//é‡?ç½®å¤´å°¾æŒ‡é’ˆ
			nStart = -1;
			nEnd = -1;
		}	
	}
	
	/**
	 * å¤„ç?†ä¸­æ–‡é‡?è¯?
	 * @param context
	 */
	private void processCount(AnalyzeContext context){
		// åˆ¤æ–­æ˜¯å?¦éœ€è¦?å?¯åŠ¨é‡?è¯?æ‰«æ??
		if(!this.needCountScan(context)){
			return;
		}
		
		if(CharacterUtil.CHAR_CHINESE == context.getCurrentCharType()){
			
			//ä¼˜å…ˆå¤„ç?†countHitsä¸­çš„hit
			if(!this.countHits.isEmpty()){
				//å¤„ç?†è¯?æ®µé˜Ÿåˆ—
				Hit[] tmpArray = this.countHits.toArray(new Hit[this.countHits.size()]);
				for(Hit hit : tmpArray){
					hit = Dictionary.getSingleton().matchWithHit(context.getSegmentBuff(), context.getCursor() , hit);
					if(hit.isMatch()){
						//è¾“å‡ºå½“å‰?çš„è¯?
						Lexeme newLexeme = new Lexeme(context.getBufferOffset() , hit.getBegin() , context.getCursor() - hit.getBegin() + 1 , Lexeme.TYPE_COUNT);
						context.addLexeme(newLexeme);
						
						if(!hit.isPrefix()){//ä¸?æ˜¯è¯?å‰?ç¼€ï¼Œhitä¸?éœ€è¦?ç»§ç»­åŒ¹é…?ï¼Œç§»é™¤
							this.countHits.remove(hit);
						}
						
					}else if(hit.isUnmatch()){
						//hitä¸?æ˜¯è¯?ï¼Œç§»é™¤
						this.countHits.remove(hit);
					}					
				}
			}				

			//*********************************
			//å¯¹å½“å‰?æŒ‡é’ˆä½?ç½®çš„å­—ç¬¦è¿›è¡Œå?•å­—åŒ¹é…?
			Hit singleCharHit = Dictionary.getSingleton().matchInQuantifierDict(context.getSegmentBuff(), context.getCursor(), 1);
			if(singleCharHit.isMatch()){//é¦–å­—æˆ?é‡?è¯?è¯?
				//è¾“å‡ºå½“å‰?çš„è¯?
				Lexeme newLexeme = new Lexeme(context.getBufferOffset() , context.getCursor() , 1 , Lexeme.TYPE_COUNT);
				context.addLexeme(newLexeme);

				//å?Œæ—¶ä¹Ÿæ˜¯è¯?å‰?ç¼€
				if(singleCharHit.isPrefix()){
					//å‰?ç¼€åŒ¹é…?åˆ™æ”¾å…¥hitåˆ—è¡¨
					this.countHits.add(singleCharHit);
				}
			}else if(singleCharHit.isPrefix()){//é¦–å­—ä¸ºé‡?è¯?å‰?ç¼€
				//å‰?ç¼€åŒ¹é…?åˆ™æ”¾å…¥hitåˆ—è¡¨
				this.countHits.add(singleCharHit);
			}
			
			
		}else{
			//è¾“å…¥çš„ä¸?æ˜¯ä¸­æ–‡å­—ç¬¦
			//æ¸…ç©ºæœªæˆ?å½¢çš„é‡?è¯?
			this.countHits.clear();
		}
		
		//ç¼“å†²åŒºæ•°æ?®å·²ç»?è¯»å®Œï¼Œè¿˜æœ‰å°šæœªè¾“å‡ºçš„é‡?è¯?
		if(context.isBufferConsumed()){
			//æ¸…ç©ºæœªæˆ?å½¢çš„é‡?è¯?
			this.countHits.clear();
		}
	}
	
	/**
	 * åˆ¤æ–­æ˜¯å?¦éœ€è¦?æ‰«æ??é‡?è¯?
	 * @return
	 */
	private boolean needCountScan(AnalyzeContext context){
		if((nStart != -1 && nEnd != -1 ) || !countHits.isEmpty()){
			//æ­£åœ¨å¤„ç?†ä¸­æ–‡æ•°è¯?,æˆ–è€…æ­£åœ¨å¤„ç?†é‡?è¯?
			return true;
		}else{
			//æ‰¾åˆ°ä¸€ä¸ªç›¸é‚»çš„æ•°è¯?
			if(!context.getOrgLexemes().isEmpty()){
				Lexeme l = context.getOrgLexemes().peekLast();
				if((Lexeme.TYPE_CNUM == l.getLexemeType() ||  Lexeme.TYPE_ARABIC == l.getLexemeType())
					&& (l.getBegin() + l.getLength() == context.getCursor())){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * æ·»åŠ æ•°è¯?è¯?å…ƒåˆ°ç»“æžœé›†
	 * @param context
	 */
	private void outputNumLexeme(AnalyzeContext context){
		if(nStart > -1 && nEnd > -1){
			//è¾“å‡ºæ•°è¯?
			Lexeme newLexeme = new Lexeme(context.getBufferOffset() , nStart , nEnd - nStart + 1 , Lexeme.TYPE_CNUM);
			context.addLexeme(newLexeme);
			
		}
	}

}
