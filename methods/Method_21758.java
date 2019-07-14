public static Object removeTableAilasFromField(Object expr,String tableAlias){
  if (expr instanceof SQLIdentifierExpr || expr instanceof SQLPropertyExpr || expr instanceof SQLVariantRefExpr) {
    String name=expr.toString().replace("`","");
    if (tableAlias != null) {
      String aliasPrefix=tableAlias + ".";
      if (name.startsWith(aliasPrefix)) {
        String newFieldName=name.replaceFirst(aliasPrefix,"");
        return new SQLIdentifierExpr(newFieldName);
      }
    }
  }
  return expr;
}
