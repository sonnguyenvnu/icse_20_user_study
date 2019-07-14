/** 
 * Sets sql parameters from two arrays: names and values.
 */
public Q setObjects(final String[] names,final Object[] values){
  init();
  if (names.length != values.length) {
    throw new DbSqlException(this,"Different number of parameter names and values");
  }
  for (int i=0; i < names.length; i++) {
    setObject(names[i],values[i]);
  }
  return _this();
}
