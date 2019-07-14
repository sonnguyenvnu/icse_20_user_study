@Override public Object plugin(Object target){
  if (target instanceof StatementHandler) {
    StatementHandler statementHandler=(StatementHandler)target;
    MetaObject metaStatementHandler=SystemMetaObject.forObject(statementHandler);
    String sql=statementHandler.getBoundSql().getSql();
    Pager pager=Pager.getAndReset();
    String newSql=sql;
    if (sql.trim().toLowerCase().startsWith("select")) {
      if (pager != null) {
        newSql=EasyOrmSqlBuilder.getInstance().getActiveDatabase().getDialect().doPaging(sql,pager.pageIndex(),pager.pageSize());
      }
      Object queryEntity=statementHandler.getParameterHandler().getParameterObject();
      if (queryEntity instanceof QueryParam && ((QueryParam)queryEntity).isForUpdate()) {
        newSql=newSql + " for update";
      }
      metaStatementHandler.setValue("delegate.boundSql.sql",newSql);
    }
  }
  return Plugin.wrap(target,this);
}
