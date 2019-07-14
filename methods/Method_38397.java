/** 
 * Stores value in database. Value is casted to sql type.
 */
public void storeValue(final PreparedStatement st,final int index,final Object value,final int dbSqlType) throws SQLException {
  T t=TypeConverterManager.get().convertType(value,sqlType);
  set(st,index,t,dbSqlType);
}
