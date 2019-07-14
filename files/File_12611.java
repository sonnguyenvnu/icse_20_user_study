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

import java.util.Stack;
import java.util.TreeSet;

/**
 * IK分�?歧义�?决器
 */
class IKArbitrator {

	IKArbitrator(){
		
	}
	
	/**
	 * 分�?歧义处�?�
//	 * @param orgLexemes
	 * @param useSmart
	 */
	void process(AnalyzeContext context , boolean useSmart){
		QuickSortSet orgLexemes = context.getOrgLexemes();
		Lexeme orgLexeme = orgLexemes.pollFirst();
		
		LexemePath crossPath = new LexemePath();
		while(orgLexeme != null){
			if(!crossPath.addCrossLexeme(orgLexeme)){
				//找到与crossPath�?相交的下一个crossPath	
				if(crossPath.size() == 1 || !useSmart){
					//crossPath没有歧义 或者 �?�?�歧义处�?�
					//直接输出当�?crossPath
					context.addLexemePath(crossPath);
				}else{
					//对当�?的crossPath进行歧义处�?�
					QuickSortSet.Cell headCell = crossPath.getHead();
					LexemePath judgeResult = this.judge(headCell, crossPath.getPathLength());
					//输出歧义处�?�结果judgeResult
					context.addLexemePath(judgeResult);
				}
				
				//把orgLexeme加入新的crossPath中
				crossPath = new LexemePath();
				crossPath.addCrossLexeme(orgLexeme);
			}
			orgLexeme = orgLexemes.pollFirst();
		}
		
		
		//处�?�最�?�的path
		if(crossPath.size() == 1 || !useSmart){
			//crossPath没有歧义 或者 �?�?�歧义处�?�
			//直接输出当�?crossPath
			context.addLexemePath(crossPath);
		}else{
			//对当�?的crossPath进行歧义处�?�
			QuickSortSet.Cell headCell = crossPath.getHead();
			LexemePath judgeResult = this.judge(headCell, crossPath.getPathLength());
			//输出歧义处�?�结果judgeResult
			context.addLexemePath(judgeResult);
		}
	}
	
	/**
	 * 歧义识别
	 * @param lexemeCell 歧义路径链表头
	 * @param fullTextLength 歧义路径文本长度
	 * @return
	 */
	private LexemePath judge(QuickSortSet.Cell lexemeCell , int fullTextLength){
		//候选路径集�?�
		TreeSet<LexemePath> pathOptions = new TreeSet<LexemePath>();
		//候选结果路径
		LexemePath option = new LexemePath();
		
		//对crossPath进行一次�??历,�?�时返回本次�??历中有冲�?的Lexeme栈
		Stack<QuickSortSet.Cell> lexemeStack = this.forwardPath(lexemeCell , option);
		
		//当�?�?元链并�?�最�?�想的，加入候选路径集�?�
		pathOptions.add(option.copy());
		
		//存在歧义�?，处�?�
		QuickSortSet.Cell c = null;
		while(!lexemeStack.isEmpty()){
			c = lexemeStack.pop();
			//回滚�?元链
			this.backPath(c.getLexeme() , option);
			//从歧义�?�?置开始，递归，生�?�?�选方案
			this.forwardPath(c , option);
			pathOptions.add(option.copy());
		}
		
		//返回集�?�中的最优方案
		return pathOptions.first();

	}
	
	/**
	 * �?��?�??历，添加�?元，构造一个无歧义�?元组�?�
//	 * @param LexemePath path
	 * @return
	 */
	private Stack<QuickSortSet.Cell> forwardPath(QuickSortSet.Cell lexemeCell , LexemePath option){
		//�?�生冲�?的Lexeme栈
		Stack<QuickSortSet.Cell> conflictStack = new Stack<QuickSortSet.Cell>();
		QuickSortSet.Cell c = lexemeCell;
		//迭代�??历Lexeme链表
		while(c != null && c.getLexeme() != null){
			if(!option.addNotCrossLexeme(c.getLexeme())){
				//�?元交�?�，添加失败则加入lexemeStack栈
				conflictStack.push(c);
			}
			c = c.getNext();
		}
		return conflictStack;
	}
	
	/**
	 * 回滚�?元链，直到它能够接�?�指定的�?元
//	 * @param lexeme
	 * @param l
	 */
	private void backPath(Lexeme l  , LexemePath option){
		while(option.checkCross(l)){
			option.removeTail();
		}
		
	}
	
}
