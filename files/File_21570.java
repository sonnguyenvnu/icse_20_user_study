package org.nlpcn.es4sql.parse;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
import com.alibaba.druid.sql.ast.expr.SQLNullExpr;
import com.google.common.base.Joiner;
import org.nlpcn.es4sql.Util;
import org.nlpcn.es4sql.domain.Condition;
import org.nlpcn.es4sql.domain.Condition.OPEAR;
import org.nlpcn.es4sql.domain.Where;
import org.nlpcn.es4sql.exception.SqlParseException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by allwefantasy on 9/3/16.
 */
public class CaseWhenParser {
    private SQLCaseExpr caseExpr;

    //zhongshu-comment 以下这两个属性貌似没有被使用
    private String alias;
    private String tableAlias;

    public CaseWhenParser(SQLCaseExpr caseExpr, String alias, String tableAlias) {
        this.alias = alias;
        this.tableAlias = tableAlias;
        this.caseExpr = caseExpr;

    }

    public String parse() throws SqlParseException {
        List<String> result = new ArrayList<String>();

        for (SQLCaseExpr.Item item : caseExpr.getItems()) {
            SQLExpr conditionExpr = item.getConditionExpr();

            WhereParser parser = new WhereParser(new SqlParser(), conditionExpr);
            /*
            zhongshu-comment 将case when的�?��?�?�件判断转�?�为script的if-else判断，举例如下
            case when：
                CASE
		        WHEN platform_id = 'PC' AND os NOT IN ('全部') THEN 'unknown'
		        ELSE os

		     script的if-else：
		        将上文case when例�?中的WHEN platform_id = 'PC' AND os NOT IN ('全部') THEN 'unknown' 解�?�?如下的script：
		        (doc['platform_id'].value=='PC') && (doc['os'].value != '全部' )
             */
            String scriptCode = explain(parser.findWhere());
            if (scriptCode.startsWith(" &&")) {
                scriptCode = scriptCode.substring(3);
            }
            if (result.size() == 0) {
                result.add("if(" + scriptCode + ")" + "{" + Util.getScriptValueWithQuote(item.getValueExpr(), "'") + "}");
            } else {
                result.add("else if(" + scriptCode + ")" + "{" + Util.getScriptValueWithQuote(item.getValueExpr(), "'") + "}");
            }

        }
        SQLExpr elseExpr = caseExpr.getElseExpr();
        if (elseExpr == null) {
            result.add("else { null }");
        } else {
            result.add("else {" + Util.getScriptValueWithQuote(elseExpr, "'") + "}");
        }

        return Joiner.on(" ").join(result);
    }

    /**
     * zhongshu-comment
     * 1�?该方法的作用：将在where�?�?�中的case when解�?为es script
     * 2�?该�?情况的es script和select�?group by�?order by等�?�?�中的case when的es script�?一样，
     *      因为在where�?�?�中script的返回值是布尔类型，所以script中需�?有个布尔判断，
     *      而其他情况的script返回值就是转�?��?�的值，该值一般是字符串�?数值
     * @author zhongshu
     * @return
     * @throws SqlParseException
     */
    public String parseCaseWhenInWhere(Object[] valueArr) throws SqlParseException {
        List<String> result = new ArrayList<String>();
        String TMP = "tmp";
        result.add("String " + TMP + " = '';");

        for (SQLCaseExpr.Item item : caseExpr.getItems()) {
            SQLExpr conditionExpr = item.getConditionExpr();

            WhereParser parser = new WhereParser(new SqlParser(), conditionExpr);

            String scriptCode = explain(parser.findWhere());
            if (scriptCode.startsWith(" &&")) {
                scriptCode = scriptCode.substring(3);
            }
            if (result.size() == 1) { //zhongshu-comment 在for循环之�?就已�?先add了一个元素
                result.add("if(" + scriptCode + ")" + "{" + TMP + "=" + Util.getScriptValueWithQuote(item.getValueExpr(), "'") + "}");
            } else {
                result.add("else if(" + scriptCode + ")" + "{" + TMP + "=" + Util.getScriptValueWithQuote(item.getValueExpr(), "'") + "}");
            }

        }
        SQLExpr elseExpr = caseExpr.getElseExpr();
        if (elseExpr == null) {
            result.add("else { null }");
        } else {
            result.add("else {" + TMP + "=" + Util.getScriptValueWithQuote(elseExpr, "'") + "}");
        }

        /*
        zhongshu-comment
        1�?第一�?情况in
        field in (a, b, c)     --> field == a || field == b || field == c

        2�?第二�?情况not in
        field not in (a, b, c) --> field != a && field != b && field != c
                         等价于 --> !(field == a || field == b || field == c) �?�对第一�?情况�?��??，
                                    (field == a || field == b || field == c)里的a�?b�?c�?全部为false，!(field == a || field == b || field == c)�?为true

        3�?这里�?�拼接第一�?情况，�?拼接第一�?情况，
            如果�?需�?第二�?情况，那就调用该方法得到返回值�?�自行拼上�?��??符�?�和括�?�: !(${该方法的返回值})
         */
        String judgeStatement = parseInNotInJudge(valueArr, TMP, "==", "||", true);
        result.add("return " + judgeStatement +  ";");
        return Joiner.on(" ").join(result);
    }

    /**
     * zhongshu-comment 这个方法应该设为private比较�?�适，因为�?�在上文的parse()方法中被调用了
     * zhongshu-comment 将case when的�?��?�?�件判断转�?�为script的if-else判断，举例如下
         case when：
         CASE
         WHEN platform_id = 'PC' AND os NOT IN ('全部') THEN 'unknown'
         ELSE os

         script的if-else：
         将上文case when例�?中的WHEN platform_id = 'PC' AND os NOT IN ('全部') THEN 'unknown' 解�?�?如下的script：
         (doc['platform_id'].value=='PC') && (doc['os'].value != '全部' )
     * @param where
     * @return
     * @throws SqlParseException
     */
    public String explain(Where where) throws SqlParseException {
        List<String> codes = new ArrayList<String>();
        while (where.getWheres().size() == 1) {
            where = where.getWheres().getFirst();
        }
        explainWhere(codes, where);
        String relation = where.getConn().name().equals("AND") ? " && " : " || ";
        return Joiner.on(relation).join(codes);
    }


    private void explainWhere(List<String> codes, Where where) throws SqlParseException {
        if (where instanceof Condition) {
            Condition condition = (Condition) where;

            if (condition.getValue() instanceof ScriptFilter) {
                codes.add(String.format("Function.identity().compose((o)->{%s}).apply(null)", ((ScriptFilter) condition.getValue()).getScript()));
            } else if (condition.getOpear() == OPEAR.BETWEEN) {
                Object[] objs = (Object[]) condition.getValue();
                codes.add("(" + "doc['" + condition.getName() + "'].value >= " + objs[0] + " && doc['"
                        + condition.getName() + "'].value <=" + objs[1] + ")");
            } else if (condition.getOpear() == OPEAR.IN) {// in
                //zhongshu-comment 增加该分支，�?�以解�?case when判断语�?�中的in�?not in判断语�?�
                codes.add(parseInNotInJudge(condition, "==", "||", false));
            } else if (condition.getOpear() == OPEAR.NIN) { // not in
                codes.add(parseInNotInJudge(condition, "!=", "&&", false));
            } else {
                SQLExpr nameExpr = condition.getNameExpr();
                SQLExpr valueExpr = condition.getValueExpr();
                if(valueExpr instanceof SQLNullExpr) {
                    //zhongshu-comment 空值查询的�?�?�?�？例如：查a字段没有值的那些文档，是这个�?�?�?�
                    codes.add("(" + "doc['" + nameExpr.toString() + "']" + ".empty)");
                } else {
                    //zhongshu-comment 该分支示例：(doc['c'].value==1)
                    codes.add("(" + Util.getScriptValueWithQuote(nameExpr, "'") + condition.getOpertatorSymbol() + Util.getScriptValueWithQuote(valueExpr, "'") + ")");
                }
            }
        } else {
            for (Where subWhere : where.getWheres()) {
                List<String> subCodes = new ArrayList<String>();
                explainWhere(subCodes, subWhere);
                String relation = subWhere.getConn().name().equals("AND") ? "&&" : "||";
                codes.add(Joiner.on(relation).join(subCodes));
            }
        }
    }

    /**
     * @author zhongshu
     * @param condition
     * @param judgeOperator
     * @param booleanOperator
     * @throws SqlParseException
     */
    private String parseInNotInJudge(Condition condition, String judgeOperator, String booleanOperator, boolean flag) throws SqlParseException {
        Object[] objArr = (Object[]) condition.getValue();
        if (objArr.length == 0)
            throw new SqlParseException("you should assign some value in bracket!!");

        String script = "(";

        String template = "doc['" + condition.getName() + "'].value " + judgeOperator + " %s " + booleanOperator + " "; //结尾这个空格就�?�空一格
        if (flag) {
            template = condition.getName() + " " + judgeOperator + " %s " + booleanOperator + " "; //结尾这个空格就�?�空一格;
        }
        for (Object obj : objArr) {
            script = script + String.format(template, parseInNotInValueWithQuote(obj));
        }
        script = script.substring(0, script.lastIndexOf(booleanOperator));//去掉末尾的&&
        script += ")"; //zhongshu-comment script结果示例 (doc['a'].value == 1 && doc['a'].value == 2 && doc['a'].value == 3 )
        return script;

    }

    private String parseInNotInJudge(Object value, String fieldName, String judgeOperator, String booleanOperator, boolean flag) throws SqlParseException {
        Condition cond = new Condition(null);
        cond.setValue(value);
        cond.setName(fieldName);

        return parseInNotInJudge(cond, judgeOperator, booleanOperator, flag);

    }

    /**
     * @author zhongshu
     * @param obj
     * @return
     */
    private Object parseInNotInValueWithQuote(Object obj) {
        //zhongshu-comment 因为我们的表就�?�有String 和 double两�?类型，所以就�?�判断了这两�?情况
        if (obj instanceof String) {
            return "'" + obj + "'";
        } else {
            return obj;
        }
    }
}
