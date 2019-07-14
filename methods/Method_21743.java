private static String scriptDeclare(SQLExpr a){
  if (isProperty(a) || a instanceof SQLNumericLiteralExpr)   return "";
 else   return Util.expr2Object(a).toString() + ";";
}
