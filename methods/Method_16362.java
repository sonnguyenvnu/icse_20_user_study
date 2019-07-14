protected RDBColumnMetaData createPrimaryKeyColumn(){
  RDBColumnMetaData id=new RDBColumnMetaData();
  id.setName("id");
  id.setJdbcType(JDBCType.VARCHAR);
  id.setJavaType(String.class);
  id.setLength(32);
  id.setDefaultValue(IDGenerator.MD5::generate);
  id.setComment("??");
  id.setPrimaryKey(true);
  id.setNotNull(true);
  id.setProperty("read-only",true);
  return id;
}
