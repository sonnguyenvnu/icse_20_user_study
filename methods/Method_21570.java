public String parse() throws SqlParseException {
  List<String> result=new ArrayList<String>();
  for (  SQLCaseExpr.Item item : caseExpr.getItems()) {
    SQLExpr conditionExpr=item.getConditionExpr();
    WhereParser parser=new WhereParser(new SqlParser(),conditionExpr);
    String scriptCode=explain(parser.findWhere());
    if (scriptCode.startsWith(" &&")) {
      scriptCode=scriptCode.substring(3);
    }
    if (result.size() == 0) {
      result.add("if(" + scriptCode + ")" + "{" + Util.getScriptValueWithQuote(item.getValueExpr(),"'") + "}");
    }
 else {
      result.add("else if(" + scriptCode + ")" + "{" + Util.getScriptValueWithQuote(item.getValueExpr(),"'") + "}");
    }
  }
  SQLExpr elseExpr=caseExpr.getElseExpr();
  if (elseExpr == null) {
    result.add("else { null }");
  }
 else {
    result.add("else {" + Util.getScriptValueWithQuote(elseExpr,"'") + "}");
  }
  return Joiner.on(" ").join(result);
}
