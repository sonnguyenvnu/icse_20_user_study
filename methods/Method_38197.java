/** 
 * Resolves list of all columns and properties.
 */
private void resolveColumnsAndProperties(final Class type){
  PropertyDescriptor[] allProperties=ClassIntrospector.get().lookup(type).getAllPropertyDescriptors();
  List<DbEntityColumnDescriptor> decList=new ArrayList<>(allProperties.length);
  int idcount=0;
  HashSet<String> names=new HashSet<>(allProperties.length);
  for (  PropertyDescriptor propertyDescriptor : allProperties) {
    DbEntityColumnDescriptor dec=DbMetaUtil.resolveColumnDescriptors(this,propertyDescriptor,isAnnotated,columnNamingStrategy);
    if (dec != null) {
      if (!names.add(dec.getColumnName())) {
        throw new DbOomException("Duplicate column name: " + dec.getColumnName());
      }
      decList.add(dec);
      if (dec.isId) {
        idcount++;
      }
    }
  }
  if (decList.isEmpty()) {
    throw new DbOomException("No column mappings in entity: " + type);
  }
  columnDescriptors=decList.toArray(new DbEntityColumnDescriptor[0]);
  Arrays.sort(columnDescriptors);
  if (idcount > 0) {
    idColumnDescriptors=new DbEntityColumnDescriptor[idcount];
    idcount=0;
    for (    DbEntityColumnDescriptor dec : columnDescriptors) {
      if (dec.isId) {
        idColumnDescriptors[idcount++]=dec;
      }
    }
  }
}
