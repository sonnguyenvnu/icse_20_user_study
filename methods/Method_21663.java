private static boolean isJoin(SQLQueryExpr sqlExpr,String sql){
  MySqlSelectQueryBlock query=(MySqlSelectQueryBlock)sqlExpr.getSubQuery().getQuery();
  return query.getFrom() instanceof SQLJoinTableSource && ((SQLJoinTableSource)query.getFrom()).getJoinType() != SQLJoinTableSource.JoinType.COMMA && sql.toLowerCase().contains("join");
}
