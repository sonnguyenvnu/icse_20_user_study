/** 
 * Registers sql type for provided type.
 */
public void register(final Class type,final Class<? extends SqlType> sqlTypeClass){
  types.put(type,lookupSqlType(sqlTypeClass));
}
