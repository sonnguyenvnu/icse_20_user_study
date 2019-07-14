public static BoolQueryBuilder explan(Where where,boolean isQuery) throws SqlParseException {
  BoolQueryBuilder boolQuery=QueryBuilders.boolQuery();
  while (where.getWheres().size() == 1) {
    where=where.getWheres().getFirst();
  }
  new QueryMaker().explanWhere(boolQuery,where);
  if (isQuery) {
    return boolQuery;
  }
  return QueryBuilders.boolQuery().filter(boolQuery);
}
