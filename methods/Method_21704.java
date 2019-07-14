private void explanWhere(BoolQueryBuilder boolQuery,Where where) throws SqlParseException {
  if (where instanceof Condition) {
    addSubQuery(boolQuery,where,(QueryBuilder)make((Condition)where));
  }
 else {
    BoolQueryBuilder subQuery=QueryBuilders.boolQuery();
    addSubQuery(boolQuery,where,subQuery);
    for (    Where subWhere : where.getWheres()) {
      explanWhere(subQuery,subWhere);
    }
  }
}
