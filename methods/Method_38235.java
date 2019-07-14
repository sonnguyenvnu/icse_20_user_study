/** 
 * Factory for result sets mapper.
 */
protected ResultSetMapper createResultSetMapper(final ResultSet resultSet){
  final Map<String,ColumnData> columnAliases=sqlgen != null ? sqlgen.getColumnData() : null;
  return new DefaultResultSetMapper(dbOom,resultSet,columnAliases,cacheEntities,this);
}
