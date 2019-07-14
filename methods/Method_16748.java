@Override public SqlAppender accept(String wherePrefix,Term term,RDBColumnMetaData column,String tableAlias){
  ChangedTermValue termValue=createChangedTermValue(term);
  RDBColumnMetaData processInstanceId=column.getTableMetaData().findColumn("processInstanceId");
  if (processInstanceId == null) {
    throw new UnsupportedOperationException("??????:[processInstanceId]????");
  }
  List<Object> val=BoostTermTypeMapper.convertList(column,termValue.getOld());
  termValue.setValue(val);
  SqlAppender appender=new SqlAppender();
  appender.add("exists(select 1 from ACT_HI_TASKINST RES WHERE ",createColumnName(processInstanceId,tableAlias),"= RES.PROC_INST_ID_ and RES.ASSIGNEE_ ");
  appendCondition(val,wherePrefix,appender);
  appender.add(")");
  return appender;
}
