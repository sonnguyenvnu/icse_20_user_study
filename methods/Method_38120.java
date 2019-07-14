/** 
 * @see #setObject(String,Object,Class,int) 
 */
public Q setObject(final String param,final Object value,final Class<? extends SqlType> sqlTypeClass,final int dbSqlType){
  init();
  final int[] positions=query.getNamedParameterIndices(param);
  for (  final int position : positions) {
    setObject(position,value,sqlTypeClass,dbSqlType);
  }
  return _this();
}
