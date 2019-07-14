/** 
 * Reads column value from result set. Since this method may be called more then once for the same column, it caches column values.
 */
@SuppressWarnings({"unchecked"}) protected Object readColumnValue(final int colNdx,final Class destinationType,final Class<? extends SqlType> sqlTypeClass,final int columnDbSqlType){
  if (colNdx != cachedColumnNdx) {
    try {
      SqlType sqlType;
      if (sqlTypeClass != null) {
        sqlType=SqlTypeManager.get().lookupSqlType(sqlTypeClass);
      }
 else {
        sqlType=SqlTypeManager.get().lookup(destinationType);
      }
      if (sqlType != null) {
        cachedColumnValue=sqlType.readValue(resultSet,colNdx + 1,destinationType,columnDbSqlType);
      }
 else {
        cachedColumnValue=resultSet.getObject(colNdx + 1);
        cachedColumnValue=TypeConverterManager.get().convertType(cachedColumnValue,destinationType);
      }
    }
 catch (    SQLException sex) {
      throw new DbOomException(dbOomQuery,"Invalid value for column #" + (colNdx + 1),sex);
    }
    cachedColumnNdx=colNdx;
  }
  return cachedColumnValue;
}
