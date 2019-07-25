@Override public Pojo duplicate(){
  SqlFieldMacro re=new SqlFieldMacro();
  re.sql=sql.duplicate();
  re.entityField=entityField;
  re.setSqlType(sql.getSqlType());
  re.setEntity(entityField.getEntity());
  re.shallDuplicate=shallDuplicate;
  return re;
}
