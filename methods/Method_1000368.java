public void execute(final Sql... sqls){
  for (  Sql sql : sqls)   expert.formatQuery(sql);
  _exec(sqls);
}
