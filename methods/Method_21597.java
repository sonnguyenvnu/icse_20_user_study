public boolean tryFillFromExpr(SQLExpr expr) throws SqlParseException {
  if (!(expr instanceof SQLMethodInvokeExpr))   return false;
  SQLMethodInvokeExpr method=(SQLMethodInvokeExpr)expr;
  String methodNameLower=method.getMethodName().toLowerCase();
  if (!(methodNameLower.equals("nested") || methodNameLower.equals("reverse_nested")))   return false;
  reverse=methodNameLower.equals("reverse_nested");
  List<SQLExpr> parameters=method.getParameters();
  int size=parameters.size();
  if (size != 3 && size != 2 && size != 1)   throw new SqlParseException("on nested object only allowed 3 parameters (path,conditions..,inner_hits) or 2 parameters (field,path)/(path,conditions..) or 1 parameter (field) ");
  if (size == 3) {
    this.innerHits=Util.extendedToString(parameters.remove(--size));
  }
  String field=Util.extendedToString(parameters.get(0));
  this.field=field;
  if (size == 1) {
    if (!field.contains(".")) {
      if (!reverse)       throw new SqlParseException("nested should contain . on their field name");
 else {
        this.path=null;
        this.simple=true;
      }
    }
 else {
      int lastDot=field.lastIndexOf(".");
      this.path=field.substring(0,lastDot);
      this.simple=true;
    }
  }
 else   if (size == 2) {
    SQLExpr secondParameter=parameters.get(1);
    if (secondParameter instanceof SQLTextLiteralExpr || secondParameter instanceof SQLIdentifierExpr || secondParameter instanceof SQLPropertyExpr) {
      String pathString=Util.extendedToString(secondParameter);
      if (pathString.equals(""))       this.path=null;
 else       this.path=pathString;
      this.simple=true;
    }
 else {
      this.path=field;
      Where where=Where.newInstance();
      new WhereParser(new SqlParser()).parseWhere(secondParameter,where);
      if (where.getWheres().size() == 0)       throw new SqlParseException("unable to parse filter where.");
      this.where=where;
      simple=false;
    }
  }
  return true;
}
