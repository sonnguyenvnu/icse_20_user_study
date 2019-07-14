public static boolean isFromJoinOrUnionTable(SQLExpr expr){
  SQLObject temp=expr;
  AtomicInteger counter=new AtomicInteger(10);
  while (temp != null && !(expr instanceof SQLSelectQueryBlock) && !(expr instanceof SQLJoinTableSource) && !(expr instanceof SQLUnionQuery) && counter.get() > 0) {
    counter.decrementAndGet();
    temp=temp.getParent();
    if (temp instanceof SQLSelectQueryBlock) {
      SQLTableSource from=((SQLSelectQueryBlock)temp).getFrom();
      if (from instanceof SQLJoinTableSource || from instanceof SQLUnionQuery) {
        return true;
      }
    }
    if (temp instanceof SQLJoinTableSource || temp instanceof SQLUnionQuery) {
      return true;
    }
  }
  return false;
}
