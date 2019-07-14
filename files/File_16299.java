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

import org.hswebframework.web.commons.entity.TreeSortSupportEntity;
import org.hswebframework.web.dict.EnumDict;
import org.hswebframework.web.dict.ItemDefine;

import java.util.List;

/**
 * 数�?�字典选项 实体
 *
 * @author hsweb-generator-online
 */
public interface DictionaryItemEntity extends TreeSortSupportEntity<String>, EnumDict<String> {
 /*-------------------------------------------
    |               属性�??常�?               |
    ===========================================*/
    /**
     * 字典id
     */
    String dictId = "dictId";
    /**
     * �??称
     */
    String name = "name";
    /**
     * 字典值
     */
    String value = "value";
    /**
     * 字典文本
     */
    String text = "text";
    /**
     * 字典值类型
     */
    String valueType = "valueType";
    /**
     * 是�?��?�用
     */
    String status = "status";
    /**
     * 说明
     */
    String describe = "describe";
    /**
     * 父级选项
     */
    String parentId = "parentId";
    /**
     * 树编�?
     */
    String path = "path";
    /**
     * 快速�?�索�?
     */
    String searchCode = "searchCode";
    /**
     * 排�?索引
     */
    String sortIndex = "sortIndex";
    /**
     * 树结构层级
     */
    String level = "level";

    /**
     * @return 字典id
     */
    String getDictId();

    /**
     * 设置 字典id
     */
    void setDictId(String dictId);

    /**
     * @return �??称
     */
    String getName();

    /**
     * 设置 �??称
     */
    void setName(String name);

    /**
     * @return 字典值
     */
    String getValue();

    /**
     * 设置 字典值
     */
    void setValue(String value);

    /**
     * @return 字典文本
     */
    String getText();

    /**
     * 设置 字典文本
     */
    void setText(String text);

    /**
     * @return 字典值类型
     */
    String getValueType();

    /**
     * 设置 字典值类型
     */
    void setValueType(String valueType);

    /**
     * @return 状�?
     */
    Byte getStatus();

    /**
     * 设置 状�?
     */
    void setStatus(Byte status);

    /**
     * @return 说明
     */
    String getDescribe();

    /**
     * 设置 说明
     */
    void setDescribe(String describe);

    /**
     * @return 快速�?�索�?
     */
    String getSearchCode();

    /**
     * 设置 快速�?�索�?
     */
    void setSearchCode(String searchCode);

    void setChildren(List<DictionaryItemEntity> children);

    Integer getOrdinal();

    void setOrdinal(Integer ordinal);

    @Override
    default int ordinal() {
        return getOrdinal() == null ? 0 : getOrdinal();
    }

    @Override
    default String getComments() {
        return getDescribe();
    }
}
