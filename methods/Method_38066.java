public Q setNull(final String param,final int value,final String typeName){
  initPrepared();
  final int[] positions=query.getNamedParameterIndices(param);
  try {
    for (    final int position : positions) {
      preparedStatement.setNull(position,value,typeName);
    }
  }
 catch (  SQLException sex) {
    throw new DbSqlException(this,"Failed to set null to parameter: " + param,sex);
  }
  return _this();
}
