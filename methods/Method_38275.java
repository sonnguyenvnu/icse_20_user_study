@Override public void process(final StringBuilder out){
  DbEntityDescriptor ded=entityName != null ? lookupName(entityName) : lookupType(entityType);
  StringBuilder col=new StringBuilder();
  StringBuilder val=new StringBuilder();
  DbEntityColumnDescriptor[] decList=ded.getColumnDescriptors();
  String typeName=StringUtil.uncapitalize(ded.getEntityName());
  int size=0;
  for (  DbEntityColumnDescriptor dec : decList) {
    if (dec.isId() && !defaultIsUpdateablePrimaryKey) {
      continue;
    }
    String property=dec.getPropertyName();
    Object value=BeanUtil.declared.getProperty(data,property);
    if (value == null) {
      continue;
    }
    if (size > 0) {
      col.append(',').append(' ');
      val.append(',').append(' ');
    }
    size++;
    col.append(dec.getColumnNameForQuery());
    final String propertyName=typeName + '.' + property;
    defineParameter(val,propertyName,value,dec);
  }
  out.append("insert into ").append(ded.getTableNameForQuery()).append(" (").append(col).append(") values (").append(val).append(')');
}
