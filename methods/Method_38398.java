/** 
 * Reads value from database. Value is casted to destination type.
 * @param rs result set
 * @param index database column index
 * @param destinationType property type
 * @param dbSqlType hint for column sql type value
 */
public <E>E readValue(final ResultSet rs,final int index,final Class<E> destinationType,final int dbSqlType) throws SQLException {
  T t=get(rs,index,dbSqlType);
  return prepareGetValue(t,destinationType);
}
