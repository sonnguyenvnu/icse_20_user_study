/** 
 * {@inheritDoc}
 */
@Override public Class[] resolveTables(){
  List<Class> classes=new ArrayList<>(tableNames.length);
  String lastTableName=null;
  resultColumns.clear();
  for (int i=0; i < tableNames.length; i++) {
    String tableName=tableNames[i];
    String columnName=columnNames[i];
    if (tableName == null) {
      throw new DbOomException(dbOomQuery,"Table name missing in meta-data");
    }
    if ((!tableName.equals(lastTableName)) || (resultColumns.contains(columnName))) {
      resultColumns.clear();
      lastTableName=tableName;
      DbEntityDescriptor ded=dbEntityManager.lookupTableName(tableName);
      if (ded == null) {
        throw new DbOomException(dbOomQuery,"Table name not registered: " + tableName);
      }
      classes.add(ded.getType());
    }
    resultColumns.add(columnName);
  }
  return classes.toArray(new Class[0]);
}
