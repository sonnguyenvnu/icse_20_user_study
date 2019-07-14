private static boolean isProperty(SQLExpr expr){
  return (expr instanceof SQLIdentifierExpr || expr instanceof SQLPropertyExpr || expr instanceof SQLVariantRefExpr);
}
