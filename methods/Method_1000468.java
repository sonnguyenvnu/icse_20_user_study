public static SqlExpression create(String name,String op,Nesting value){
  op=Strings.trim(op.toUpperCase());
  if (value == null) {
    throw Lang.makeThrow("nesting sql can not be null'");
  }
 else   if ("LIKE".equals(op) || "NOT LIKE".equals(op)) {
    return like(name,value).setNot(op.startsWith("NOT"));
  }
 else   if ("=".equals(op)) {
    return eq(name,value);
  }
 else   if ("!=".equals(op) || "<>".equals(op)) {
    return notEq(name,value);
  }
 else   if ("IN".equals(op) || "NOT IN".equals(op)) {
    return inSql(name,value).setNot(op.startsWith("NOT"));
  }
 else   if ("EXISTS".equals(op) || "NOT EXISTS".equals(op)) {
    return exists(value).setNot(op.startsWith("NOT"));
  }
  return otherSymbol(name,op,value);
}
