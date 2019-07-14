/** 
 * Returns sql type instance. Instances are stored for better performances.
 */
public SqlType lookupSqlType(final Class<? extends SqlType> sqlTypeClass){
  SqlType sqlType=sqlTypes.get(sqlTypeClass);
  if (sqlType == null) {
    try {
      sqlType=ClassUtil.newInstance(sqlTypeClass);
    }
 catch (    Exception ex) {
      throw new DbSqlException("SQL type not found: " + sqlTypeClass.getSimpleName(),ex);
    }
    sqlTypes.put(sqlTypeClass,sqlType);
  }
  return sqlType;
}
