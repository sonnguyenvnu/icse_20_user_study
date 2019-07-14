/** 
 * Parse the from clause zhongshu-comment ?????????join??????????
 * @param from the from clause.
 * @return list of From objects represents all the sources.
 */
private List<From> findFrom(SQLTableSource from){
  boolean isSqlExprTable=from.getClass().isAssignableFrom(SQLExprTableSource.class);
  if (isSqlExprTable) {
    SQLExprTableSource fromExpr=(SQLExprTableSource)from;
    String[] split=fromExpr.getExpr().toString().split(",");
    ArrayList<From> fromList=new ArrayList<>();
    for (    String source : split) {
      fromList.add(new From(source.trim(),fromExpr.getAlias()));
    }
    return fromList;
  }
  SQLJoinTableSource joinTableSource=((SQLJoinTableSource)from);
  List<From> fromList=new ArrayList<>();
  fromList.addAll(findFrom(joinTableSource.getLeft()));
  fromList.addAll(findFrom(joinTableSource.getRight()));
  return fromList;
}
