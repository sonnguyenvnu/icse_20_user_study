@Override public SqlAppender accept(String wherePrefix,Term term,RDBColumnMetaData column,String tableAlias){
  ChangedTermValue termValue=createChangedTermValue(term);
  SqlAppender appender=new SqlAppender();
  appender.add(not ? "not " : "","exists(select 1 from s_user_role tmp where tmp.user_id =",createColumnName(column,tableAlias));
  List<Object> positionIdList=BoostTermTypeMapper.convertList(column,termValue.getOld());
  if (!positionIdList.isEmpty()) {
    appender.add(" and tmp.role_id");
    termValue.setValue(appendCondition(positionIdList,wherePrefix,appender));
  }
  appender.add(")");
  return appender;
}
