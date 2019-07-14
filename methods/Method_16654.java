@Override public SqlAppender accept(String wherePrefix,Term term,RDBColumnMetaData column,String tableAlias){
  ChangedTermValue termValue=createChangedTermValue(term);
  Dialect dialect=column.getTableMetaData().getDatabaseMetaData().getDialect();
  SqlAppender appender=new SqlAppender();
  appender.addSpc(not ? "not" : "","exists(select 1 from ",getTableFullName("s_person_position")," _tmp");
  if (isChild() || isParent()) {
    appender.addSpc(",",getTableFullName("s_position")," _pos");
  }
  if (!isForPerson()) {
    appender.addSpc(",",getTableFullName("s_person")," _person");
  }
  appender.addSpc("where ",createColumnName(column,tableAlias),"=",isForPerson() ? " _tmp.person_id" : "_person.user_id and _person.u_id=_tmp.person_id");
  if (isChild() || isParent()) {
    appender.addSpc("and _pos.u_id=_tmp.position_id");
  }
  List<Object> positionIdList=BoostTermTypeMapper.convertList(column,termValue.getOld());
  if (!positionIdList.isEmpty()) {
    appender.addSpc("and");
    termValue.setValue(appendCondition(positionIdList,wherePrefix,appender,"_tmp.position_id",dialect));
  }
  appender.add(")");
  return appender;
}
