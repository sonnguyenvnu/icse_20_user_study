private void ensureSingleIdColumn(){
  init();
  if (idColumnDescriptors == null) {
    throw new DbOomException("No identity column in entity: " + entityName);
  }
 else   if (idColumnDescriptors.length > 1) {
    throw new DbOomException("More then one identity column in entity: " + entityName);
  }
}
