/** 
 * zhongshu-comment 1??????????where????case when???es script 2??????es script?select?group by?order by?????case when?es script???? ???where???script????????????script?????????? ??????script??????????????????????
 * @author zhongshu
 * @return
 * @throws SqlParseException
 */
public String parseCaseWhenInWhere(Object[] valueArr) throws SqlParseException {
  List<String> result=new ArrayList<String>();
  String TMP="tmp";
  result.add("String " + TMP + " = '';");
  for (  SQLCaseExpr.Item item : caseExpr.getItems()) {
    SQLExpr conditionExpr=item.getConditionExpr();
    WhereParser parser=new WhereParser(new SqlParser(),conditionExpr);
    String scriptCode=explain(parser.findWhere());
    if (scriptCode.startsWith(" &&")) {
      scriptCode=scriptCode.substring(3);
    }
    if (result.size() == 1) {
      result.add("if(" + scriptCode + ")" + "{" + TMP + "=" + Util.getScriptValueWithQuote(item.getValueExpr(),"'") + "}");
    }
 else {
      result.add("else if(" + scriptCode + ")" + "{" + TMP + "=" + Util.getScriptValueWithQuote(item.getValueExpr(),"'") + "}");
    }
  }
  SQLExpr elseExpr=caseExpr.getElseExpr();
  if (elseExpr == null) {
    result.add("else { null }");
  }
 else {
    result.add("else {" + TMP + "=" + Util.getScriptValueWithQuote(elseExpr,"'") + "}");
  }
  String judgeStatement=parseInNotInJudge(valueArr,TMP,"==","||",true);
  result.add("return " + judgeStatement + ";");
  return Joiner.on(" ").join(result);
}
