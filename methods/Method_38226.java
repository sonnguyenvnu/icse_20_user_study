/** 
 * Resolves column descriptor from property. If property is annotated value will be read from annotation. If property is not annotated, then property will be ignored if entity is annotated. Otherwise, column name is generated from the property name.
 */
public static DbEntityColumnDescriptor resolveColumnDescriptors(final DbEntityDescriptor dbEntityDescriptor,final PropertyDescriptor property,final boolean isAnnotated,final ColumnNamingStrategy columnNamingStrategy){
  String columnName=null;
  boolean isId=false;
  Class<? extends SqlType> sqlTypeClass=null;
  DbId dbId=null;
  if (property.getFieldDescriptor() != null) {
    dbId=property.getFieldDescriptor().getField().getAnnotation(DbId.class);
  }
  if (dbId == null && property.getReadMethodDescriptor() != null) {
    dbId=property.getReadMethodDescriptor().getMethod().getAnnotation(DbId.class);
  }
  if (dbId == null && property.getWriteMethodDescriptor() != null) {
    dbId=property.getWriteMethodDescriptor().getMethod().getAnnotation(DbId.class);
  }
  if (dbId != null) {
    columnName=dbId.value().trim();
    sqlTypeClass=dbId.sqlType();
    isId=true;
  }
 else {
    DbColumn dbColumn=null;
    if (property.getFieldDescriptor() != null) {
      dbColumn=property.getFieldDescriptor().getField().getAnnotation(DbColumn.class);
    }
    if (dbColumn == null && property.getReadMethodDescriptor() != null) {
      dbColumn=property.getReadMethodDescriptor().getMethod().getAnnotation(DbColumn.class);
    }
    if (dbColumn == null && property.getWriteMethodDescriptor() != null) {
      dbColumn=property.getWriteMethodDescriptor().getMethod().getAnnotation(DbColumn.class);
    }
    if (dbColumn != null) {
      columnName=dbColumn.value().trim();
      sqlTypeClass=dbColumn.sqlType();
    }
 else {
      if (isAnnotated) {
        return null;
      }
    }
  }
  if (StringUtil.isEmpty(columnName)) {
    columnName=columnNamingStrategy.convertPropertyNameToColumnName(property.getName());
  }
 else {
    if (!columnNamingStrategy.isStrictAnnotationNames()) {
      columnName=columnNamingStrategy.applyToColumnName(columnName);
    }
  }
  if (sqlTypeClass == SqlType.class) {
    sqlTypeClass=null;
  }
  return new DbEntityColumnDescriptor(dbEntityDescriptor,quoteIfRequired(columnName,columnNamingStrategy.isAlwaysQuoteNames(),columnNamingStrategy.getQuoteChar()),property.getName(),property.getType(),isId,sqlTypeClass);
}
