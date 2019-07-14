private String parseInNotInJudge(Object value,String fieldName,String judgeOperator,String booleanOperator,boolean flag) throws SqlParseException {
  Condition cond=new Condition(null);
  cond.setValue(value);
  cond.setName(fieldName);
  return parseInNotInJudge(cond,judgeOperator,booleanOperator,flag);
}
