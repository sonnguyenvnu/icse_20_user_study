int[] getNamedParameterIndices(final String name){
  final DbQueryNamedParameter p=lookupNamedParameter(name);
  if (p == null) {
    throw new DbSqlException("Named parameter not found: " + name + "\nQuery: " + sql);
  }
  return p.indices;
}
