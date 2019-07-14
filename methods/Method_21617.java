private Map<String,Where> splitAndFindWhere(SQLExpr whereExpr,String firstTableAlias,String secondTableAlias) throws SqlParseException {
  WhereParser whereParser=new WhereParser(this,whereExpr);
  Where where=whereParser.findWhere();
  return splitWheres(where,firstTableAlias,secondTableAlias);
}
