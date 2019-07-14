/** 
 * Create filters based on the Where clause.
 * @param where the 'WHERE' part of the SQL query.
 * @throws SqlParseException
 */
private void setWhere(Where where) throws SqlParseException {
  if (where != null) {
    QueryBuilder whereQuery=QueryMaker.explan(where);
    request.filter(whereQuery);
  }
 else {
    request.filter(QueryBuilders.matchAllQuery());
  }
}
