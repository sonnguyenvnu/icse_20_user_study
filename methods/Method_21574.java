/** 
 * @author zhongshu
 * @param condition
 * @param judgeOperator
 * @param booleanOperator
 * @throws SqlParseException
 */
private String parseInNotInJudge(Condition condition,String judgeOperator,String booleanOperator,boolean flag) throws SqlParseException {
  Object[] objArr=(Object[])condition.getValue();
  if (objArr.length == 0)   throw new SqlParseException("you should assign some value in bracket!!");
  String script="(";
  String template="doc['" + condition.getName() + "'].value " + judgeOperator + " %s " + booleanOperator + " ";
  if (flag) {
    template=condition.getName() + " " + judgeOperator + " %s " + booleanOperator + " ";
  }
  for (  Object obj : objArr) {
    script=script + String.format(template,parseInNotInValueWithQuote(obj));
  }
  script=script.substring(0,script.lastIndexOf(booleanOperator));
  script+=")";
  return script;
}
