@Override public SqlAppender accept(String wherePrefix,Term term,RDBColumnMetaData column,String tableAlias){
  ChangedTermValue termValue=createChangedTermValue(term);
  Dialect dialect=column.getTableMetaData().getDatabaseMetaData().getDialect();
  SqlAppender appender=new SqlAppender();
  appender.addSpc(not ? "not" : "","exists(select 1 from ",getTableFullName("s_person_position")," _tmp,",getTableFullName("s_position")," _pos,",getTableFullName("s_department")," _dept,",getTableFullName("s_person")," _person");
  if (isChild() || isParent()) {
    appender.addSpc(",",getTableFullName("s_organization")," _org");
  }
  appender.addSpc("where _person.u_id=_tmp.person_id and _tmp.position_id = _pos.u_id and _person.u_id=_tmp.person_id and _dept.u_id=_pos.department_id","and",createColumnName(column,tableAlias),"=",isForPerson() ? "_tmp.person_id" : "_person.user_id");
  if (isChild() || isParent()) {
    appender.addSpc("and _org.u_id=_dept.org_id");
  }
  List<Object> positionIdList=BoostTermTypeMapper.convertList(column,termValue.getOld());
  if (!positionIdList.isEmpty()) {
    appender.addSpc("and");
    termValue.setValue(appendCondition(positionIdList,wherePrefix,appender,"_dept.org_id",dialect));
  }
  appender.add(")");
  return appender;
}
