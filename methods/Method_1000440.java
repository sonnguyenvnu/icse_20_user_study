private String _colname(Entity<?> en,int index){
  MappingField field=en.getField(names[index]);
  if (field == null)   throw new IllegalArgumentException(String.format("Class %s didn't have field named (%s)",en.getType(),names[index]));
  return field.getColumnNameInSql();
}
