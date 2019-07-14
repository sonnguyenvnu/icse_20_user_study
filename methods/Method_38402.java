/** 
 * Retrieves SQL type for provided type. All subclasses and interfaces are examined for matching sql type.
 */
public SqlType lookup(final Class clazz){
  SqlType sqlType;
  for (Class x=clazz; x != null; x=x.getSuperclass()) {
    sqlType=types.get(clazz);
    if (sqlType != null) {
      return sqlType;
    }
    Class[] interfaces=x.getInterfaces();
    for (    Class i : interfaces) {
      sqlType=types.get(i);
      if (sqlType != null) {
        return sqlType;
      }
    }
  }
  return null;
}
