/** 
 * Sets the value of the designated parameter with the given object. This method is like the method <code>setObject</code> above, except that it assumes a scale of zero.
 */
void setObject(final String param,final Object object,final int targetSqlType,final int scale){
  initPrepared();
  final int[] positions=query.getNamedParameterIndices(param);
  try {
    for (    final int position : positions) {
      preparedStatement.setObject(position,object,targetSqlType,scale);
    }
  }
 catch (  SQLException sex) {
    throwSetParamError(param,sex);
  }
}
