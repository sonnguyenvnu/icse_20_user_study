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
 */
package org.wltea.analyzer.core;

import org.wltea.analyzer.cfg.Configuration;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * IK分�?器主类
 *
 */
public final class IKSegmenter {
	
	//字符窜reader
	private Reader input;
	//分�?器上下文
	private AnalyzeContext context;
	//分�?处�?�器列表
	private List<ISegmenter> segmenters;
	//分�?歧义�?决器
	private IKArbitrator arbitrator;
    private  Configuration configuration;
	

	/**
	 * IK分�?器构造函数
	 * @param input
     */
	public IKSegmenter(Reader input ,Configuration configuration){
		this.input = input;
        this.configuration = configuration;
        this.init();
	}

	
	/**
	 * �?始化
	 */
	private void init(){
		//�?始化分�?上下文
		this.context = new AnalyzeContext(configuration);
		//加载�?分�?器
		this.segmenters = this.loadSegmenters();
		//加载歧义�?决器
		this.arbitrator = new IKArbitrator();
	}
	
	/**
	 * �?始化�?典，加载�?分�?器实现
	 * @return List<ISegmenter>
	 */
	private List<ISegmenter> loadSegmenters(){
		List<ISegmenter> segmenters = new ArrayList<ISegmenter>(4);
		//处�?�字�?的�?分�?器
		segmenters.add(new LetterSegmenter()); 
		//处�?�中文数�?�?的�?分�?器
		segmenters.add(new CN_QuantifierSegmenter());
		//处�?�中文�?的�?分�?器
		segmenters.add(new CJKSegmenter());
		return segmenters;
	}
	
	/**
	 * 分�?，获�?�下一个�?元
	 * @return Lexeme �?元对象
	 * @throws java.io.IOException
	 */
	public synchronized Lexeme next()throws IOException{
		Lexeme l = null;
		while((l = context.getNextLexeme()) == null ){
			/*
			 * 从reader中读�?�数�?�，填充buffer
			 * 如果reader是分次读入buffer的，那么buffer�?  进行移�?处�?�
			 * 移�?处�?�上次读入的但未处�?�的数�?�
			 */
			int available = context.fillBuffer(this.input);
			if(available <= 0){
				//reader已�?读完
				context.reset();
				return null;
				
			}else{
				//�?始化指针
				context.initCursor();
				do{
        			//�??历�?分�?器
        			for(ISegmenter segmenter : segmenters){
        				segmenter.analyze(context);
        			}
        			//字符缓冲区接近读完，需�?读入新的字符
        			if(context.needRefillBuffer()){
        				break;
        			}
   				//�?��?移动指针
				}while(context.moveCursor());
				//�?置�?分�?器，为下轮循环进行�?始化
				for(ISegmenter segmenter : segmenters){
					segmenter.reset();
				}
			}
			//对分�?进行歧义处�?�
			this.arbitrator.process(context, configuration.isUseSmart());
			//将分�?结果输出到结果集，并处�?�未切分的�?�个CJK字符
			context.outputToResult();
			//记录本次分�?的缓冲区�?移
			context.markBufferOffset();			
		}
		return l;
	}

	/**
     * �?置分�?器到�?始状�?
     * @param input
     */
	public synchronized void reset(Reader input) {
		this.input = input;
		context.reset();
		for(ISegmenter segmenter : segmenters){
			segmenter.reset();
		}
	}
}
