/** 
 * Populates entity with generated column values from executed query.
 */
public void populateGeneratedKeys(final Object entity){
  final String[] generatedColumns=getGeneratedColumnNames();
  if (generatedColumns == null) {
    return;
  }
  DbEntityDescriptor ded=dbOom.entityManager().lookupType(entity.getClass());
  Class[] keyTypes=new Class[generatedColumns.length];
  String[] properties=new String[generatedColumns.length];
  for (int i=0; i < generatedColumns.length; i++) {
    String column=generatedColumns[i];
    DbEntityColumnDescriptor decd=ded.findByColumnName(column);
    if (decd != null) {
      keyTypes[i]=decd.getPropertyType();
      properties[i]=decd.getPropertyName();
    }
  }
  final Object keyValues=findGeneratedColumns(keyTypes);
  if (!keyValues.getClass().isArray()) {
    BeanUtil.declared.setProperty(entity,properties[0],keyValues);
  }
 else {
    for (int i=0; i < properties.length; i++) {
      BeanUtil.declared.setProperty(entity,properties[i],((Object[])keyValues)[i]);
    }
  }
}
