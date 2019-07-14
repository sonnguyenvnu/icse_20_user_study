package org.hswebframework.web;

import org.hswebframework.expands.script.engine.DynamicScriptEngine;
import org.hswebframework.expands.script.engine.DynamicScriptEngineFactory;
import org.hswebframework.expands.script.engine.ExecuteResult;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * è¡¨è¾¾å¼?å·¥å…·,ç”¨æˆ·è§£æž?è¡¨è¾¾å¼?ä¸ºå­—ç¬¦ä¸²
 *
 * @author zhouhao
 * @since 3.0
 */
public class ExpressionUtils {

    //è¡¨è¾¾å¼?æ??å?–æ­£åˆ™ ${.+?}
    private static final Pattern PATTERN = Pattern.compile("(?<=\\$\\{)(.+?)(?=})");

    /**
     * èŽ·å?–é»˜è®¤çš„è¡¨è¾¾å¼?å?˜é‡?
     *
     * @return å?˜é‡?é›†å?ˆ
     */
    public static Map<String, Object> getDefaultVar() {
        return new HashMap<>();
    }

    /**
     * èŽ·å?–é»˜è®¤çš„è¡¨è¾¾å¼?å?˜é‡?å¹¶å°†åˆ¶å®šçš„å?˜é‡?å?ˆå¹¶åœ¨ä¸€èµ·
     *
     * @param var è¦?å?ˆå¹¶çš„å?˜é‡?é›†å?ˆ
     * @return å?˜é‡?é›†å?ˆ
     */
    public static Map<String, Object> getDefaultVar(Map<String, Object> var) {
        Map<String, Object> vars = getDefaultVar();
        vars.putAll(var);
        return vars;
    }

    /**
     * ä½¿ç”¨é»˜è®¤çš„å?˜é‡?è§£æž?è¡¨è¾¾å¼?
     *
     * @param expression è¡¨è¾¾å¼?å­—ç¬¦ä¸²
     * @param language   è¡¨è¾¾å¼?è¯­è¨€
     * @return è§£æž?ç»“æžœ
     * @throws Exception è§£æž?é”™è¯¯
     * @see ExpressionUtils#analytical(String, Map, String)
     */
    public static String analytical(String expression, String language) throws Exception {
        return analytical(expression, new HashMap<>(), language);
    }

    /**
     * è§£æž?è¡¨è¾¾å¼?,è¡¨è¾¾å¼?ä½¿ç”¨{@link ExpressionUtils#PATTERN}è¿›è¡Œæ??å?–<br>
     * å¦‚è°ƒç”¨ analytical("http://${3+2}/test",var,"spel")<br>
     * æ”¯æŒ?çš„è¡¨è¾¾å¼?è¯­è¨€:
     * <ul>
     * <li>freemarker</li>
     * <li>spel</li>
     * <li>ognl</li>
     * <li>groovy</li>
     * <li>js</li>
     * </ul>
     *
     * @param expression è¡¨è¾¾å¼?å­—ç¬¦ä¸²
     * @param vars       å?˜é‡?
     * @param language   è¡¨è¾¾å¼?è¯­è¨€
     * @return è§£æž?ç»“æžœ
     * @throws Exception è§£æž?é”™è¯¯
     */
    public static String analytical(String expression, Map<String, Object> vars, String language) throws Exception {
        Matcher matcher = PATTERN.matcher(expression);
        DynamicScriptEngine engine = DynamicScriptEngineFactory.getEngine(language);
        if (engine == null) {
            return expression;
        }
        vars = new HashMap<>(vars);
        vars.putAll(getDefaultVar());
        while (matcher.find()) {
            String real_expression = matcher.group();
            String e_id = String.valueOf(real_expression.hashCode());
            if (!engine.compiled(e_id)) {
                engine.compile(e_id, real_expression);
            }
            ExecuteResult result = engine.execute(e_id, vars);
            if (!result.isSuccess()) {
                throw new RuntimeException(result.getMessage(), result.getException());
            }
            String obj = String.valueOf(result.get());
            // expression = matcher.replaceFirst(obj);
            expression = expression.replace("${" + real_expression + "}", obj);
        }
        return expression;
    }

}
