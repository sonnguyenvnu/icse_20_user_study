/**
 * IK 中文分�?  版本 5.0.1
 * IK Analyzer release 5.0.1
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
package org.wltea.analyzer.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.wltea.analyzer.cfg.Configuration;

/**
 * IK分�?器，Lucene Analyzer接�?�实现
 * 兼容Lucene 4.0版本
 */
public final class IKAnalyzer extends Analyzer{
	
	private Configuration configuration;

	/**
	 * IK分�?器Lucene  Analyzer接�?�实现类
	 * 
	 * 默认细粒度切分算法
	 */
	public IKAnalyzer(){
	}

    /**
	 * IK分�?器Lucene Analyzer接�?�实现类
	 * 
	 * @param configuration IK�?置
	 */
	public IKAnalyzer(Configuration configuration){
		super();
        this.configuration = configuration;
	}


	/**
	 * �?载Analyzer接�?�，构造分�?组件
	 */
	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer _IKTokenizer = new IKTokenizer(configuration);
		return new TokenStreamComponents(_IKTokenizer);
    }

}
