/** 
 * {@inheritDoc}
 */
@Override public Object[] parseObjects(final Class... types){
  resultColumns.clear();
  int totalTypes=types.length;
  Object[] result=new Object[totalTypes];
  boolean[] resultUsage=new boolean[totalTypes];
  DbEntityDescriptor[] dbEntityDescriptors=resolveDbEntityDescriptors(types);
  String[] typesTableNames=resolveTypesTableNames(types);
  String[][] mappedNames=resolveMappedTypesTableNames(types);
  int currentResult=0;
  cachedColumnNdx=-1;
  int colNdx=0;
  while (colNdx < totalColumns) {
    if (currentResult >= totalTypes) {
      break;
    }
    Class currentType=types[currentResult];
    if (currentType == null) {
      colNdx++;
      currentResult++;
      resultColumns.clear();
      continue;
    }
    String columnName=columnNames[colNdx];
    int columnDbSqlType=columnDbSqlTypes[colNdx];
    String tableName=tableNames[colNdx];
    String resultTableName=typesTableNames[currentResult];
    if (resultTableName == null) {
      result[currentResult]=readColumnValue(colNdx,currentType,null,columnDbSqlType);
      resultUsage[currentResult]=true;
      colNdx++;
      currentResult++;
      resultColumns.clear();
      continue;
    }
    boolean tableMatched=false;
    if (tableName == null) {
      tableMatched=true;
    }
 else     if (resultTableName.equals(tableName)) {
      tableMatched=true;
    }
 else {
      String[] mapped=mappedNames[currentResult];
      if (mapped != null) {
        for (        String m : mapped) {
          if (m.equals(tableName)) {
            tableMatched=true;
            break;
          }
        }
      }
    }
    if (tableMatched) {
      if (!resultColumns.contains(columnName)) {
        DbEntityDescriptor ded=dbEntityDescriptors[currentResult];
        DbEntityColumnDescriptor dec=ded.findByColumnName(columnName);
        String propertyName=(dec == null ? null : dec.getPropertyName());
        if (propertyName != null) {
          if (result[currentResult] == null) {
            result[currentResult]=dbEntityManager.createEntityInstance(currentType);
          }
          Class type=BeanUtil.declared.getPropertyType(result[currentResult],propertyName);
          if (type != null) {
            dec.updateDbSqlType(columnDbSqlType);
            Class<? extends SqlType> sqlTypeClass=dec.getSqlTypeClass();
            Object value=readColumnValue(colNdx,type,sqlTypeClass,columnDbSqlType);
            if (value != null) {
              BeanUtil.declared.setProperty(result[currentResult],propertyName,value);
              resultUsage[currentResult]=true;
            }
            colNdx++;
            resultColumns.add(columnName);
            continue;
          }
        }
      }
    }
    currentResult++;
    resultColumns.clear();
  }
  resultColumns.clear();
  for (int i=0; i < resultUsage.length; i++) {
    if (!resultUsage[i]) {
      result[i]=null;
    }
  }
  if (cacheEntities) {
    cacheResultSetEntities(result);
  }
  return result;
}
