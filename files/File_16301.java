/*
 *  Copyright 2019 http://www.hswebframework.org
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 */
package org.hswebframework.web.dictionary.api.entity;

import org.hswebframework.web.commons.entity.SimpleGenericEntity;

/**
 * 数�?�字典解�?�?置
 *
 * @author hsweb-generator-online
 */
public class SimpleDictionaryParserEntity extends SimpleGenericEntity<String> implements DictionaryParserEntity {
    //值到文本转�?�方�?
    private String valueToTextParser;
    //文本到值转�?�方�?
    private String textToValueParser;
    //转�?�失败时的�?作
    private String onError;
    //创建时间
    private Long   createTime;
    //创建人id
    private String creatorId;
    //更新时间
    private Long   updateTime;
    //�??称
    private String name;
    //说明
    private String describe;
    //分类id
    private String classifiedId;

    /**
     * @return 值到文本转�?�方�?
     */
    @Override
    public String getValueToTextParser() {
        return this.valueToTextParser;
    }

    /**
     * 设置 值到文本转�?�方�?
     */
    @Override
    public void setValueToTextParser(String valueToTextParser) {
        this.valueToTextParser = valueToTextParser;
    }

    /**
     * @return 文本到值转�?�方�?
     */
    @Override
    public String getTextToValueParser() {
        return this.textToValueParser;
    }

    /**
     * 设置 文本到值转�?�方�?
     */
    @Override
    public void setTextToValueParser(String textToValueParser) {
        this.textToValueParser = textToValueParser;
    }

    /**
     * @return 转�?�失败时的�?作
     */
    @Override
    public String getOnError() {
        return this.onError;
    }

    /**
     * 设置 转�?�失败时的�?作
     */
    @Override
    public void setOnError(String onError) {
        this.onError = onError;
    }

    /**
     * @return 创建时间
     */
    @Override
    public Long getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置 创建时间
     */
    @Override
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * @return 创建人id
     */
    @Override
    public String getCreatorId() {
        return this.creatorId;
    }

    /**
     * 设置 创建人id
     */
    @Override
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * @return 更新时间
     */
    @Override
    public Long getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置 更新时间
     */
    @Override
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return �??称
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * 设置 �??称
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return 说明
     */
    @Override
    public String getDescribe() {
        return this.describe;
    }

    /**
     * 设置 说明
     */
    @Override
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * @return 分类id
     */
    @Override
    public String getClassifiedId() {
        return this.classifiedId;
    }

    /**
     * 设置 分类id
     */
    @Override
    public void setClassifiedId(String classifiedId) {
        this.classifiedId = classifiedId;
    }
}
