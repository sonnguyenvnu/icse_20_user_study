private static boolean isMulti(SQLQueryExpr sqlExpr){
  return sqlExpr.getSubQuery().getQuery() instanceof SQLUnionQuery;
}
