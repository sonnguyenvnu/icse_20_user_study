@Override protected SqlAppender getParamString(String paramName,RDBColumnMetaData rdbColumnMetaData){
  String typeHandler=rdbColumnMetaData.getProperty("typeHandler").getValue();
  return new SqlAppender().add("#{",paramName,",javaType=",EasyOrmSqlBuilder.getJavaType(rdbColumnMetaData.getJavaType()),",jdbcType=",rdbColumnMetaData.getJdbcType(),typeHandler != null ? ",typeHandler=" + typeHandler : "","}");
}
