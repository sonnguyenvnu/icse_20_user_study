protected SqlAppender buildNotSupport(String wherePrefix,Term term,RDBColumnMetaData column,String tableAlias){
  ChangedTermValue termValue=createChangedTermValue(term);
  Object newValue=BoostTermTypeMapper.convertValue(column,termValue.getOld());
  termValue.setValue(newValue);
  Dialect dialect=column.getTableMetaData().getDatabaseMetaData().getDialect();
  String columnName=dialect.buildColumnName(tableAlias,column.getName());
  SqlAppender appender=new SqlAppender();
  appender.add(columnName,not ? " != " : "=","#{",wherePrefix,".value.value}");
  return appender;
}
