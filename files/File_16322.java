package org.hswebframework.web.dictionary.simple;

import org.hswebframework.web.dictionary.api.DictionaryParser;
import org.hswebframework.web.dictionary.api.entity.DictionaryEntity;
import org.hswebframework.web.dictionary.api.entity.DictionaryItemEntity;
import org.hswebframework.web.dictionary.api.parser.SingleDictParser;
import org.hswebframework.web.dictionary.simple.parser.SimpleSingleDictParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 简�?�的字典解�?器实现,支�?树形结构字典
 * <p>
 * e.g.
 * <pre>
 *   //字典
 *   [
 *    {text:"苹果",value:1,
 *     children:[
 *      {text:"�?�苹果",value:101},
 *      {text:"红富士",value:102},
 *      {text:"其他苹果",value:103,textExpression:"其他苹果(${#context.otherApple})"}
 *    ]}
 *    {text:"梨�?",value:2}
 *   ]
 *   //调用
 *   parser.valueToText("1,101,103",{otherApple:"其他苹果1"});
 *   //返回结果  苹果(�?�苹果,其他苹果(其他苹果1))
 *
 *   //调用
 *   parser.textToValue("苹果(�?�苹果,其他苹果)")
 *   //返回结果 1,101,103
 * </pre>
 *
 * @author zhouhao
 * @since 3.0
 */
public class SimpleDictionaryParser<V> implements DictionaryParser<V> {

    private SingleDictParser toTextParser;

    private SingleDictParser toValueParser;

    private Map<String, String> toTextExpressions = new HashMap<>();

    private Map<String, String> toValueExpressions = new HashMap<>();

    public SimpleDictionaryParser<V> addToTextExpression(String id, String expression) {
        toTextExpressions.put(id, expression);
        return this;
    }

    public SimpleDictionaryParser<V> addToValueExpression(String id, String expression) {
        toValueExpressions.put(id, expression);
        return this;
    }

    public void setToTextExpressions(Map<String, String> toTextExpressions) {
        this.toTextExpressions = toTextExpressions;
    }

    public void setToValueExpressions(Map<String, String> toValueExpressions) {
        this.toValueExpressions = toValueExpressions;
    }

    public SingleDictParser getToTextParser() {
        return toTextParser;
    }

    public SingleDictParser getToValueParser() {
        return toValueParser;
    }

    public void setToTextParser(SingleDictParser toTextParser) {
        this.toTextParser = toTextParser;
    }

    public void setToValueParser(SingleDictParser toValueParser) {
        this.toValueParser = toValueParser;
    }

    //设置DictionaryEntity作为�?置
    public SimpleDictionaryParser<V> setDict(DictionaryEntity dict) {
        SimpleSingleDictParser toTextParser = new SimpleSingleDictParser();
        toTextParser.setDict(dict, DictionaryItemEntity::getValue
                , DictionaryItemEntity::getText
                , item -> toTextExpressions.get(item.getId()));

        SimpleSingleDictParser toValueParser = new SimpleSingleDictParser();
        toValueParser.setDict(dict, DictionaryItemEntity::getText
                , DictionaryItemEntity::getValue,
                item -> toValueExpressions.get(item.getId()));

        toValueParser.getTargetFormat().setSplitter(",");
        toValueParser.getTargetFormat().setChildStartChar(",");
        toValueParser.getTargetFormat().setChildEndChar("");
        toValueParser.getTargetFormat().setChildSplitter(",");
        this.setToTextParser(toTextParser);
        this.setToValueParser(toValueParser);
        return this;
    }

    @Override
    public Optional<String> valueToText(V value, Object context) {
        if (value == null) {
            return Optional.empty();
        }
        return toTextParser.parse(String.valueOf(value), context);
    }

    @Override
    public Optional<V> textToValue(String text, Object context) {
        return toValueParser.parse(text, context).map(v -> (V) v);
    }
}
