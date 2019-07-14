public static Field makeField(SQLExpr expr,String alias,String tableAlias) throws SqlParseException {
  if (expr instanceof SQLIdentifierExpr || expr instanceof SQLPropertyExpr || expr instanceof SQLVariantRefExpr) {
    return handleIdentifier(expr,alias,tableAlias);
  }
 else   if (expr instanceof SQLQueryExpr) {
    throw new SqlParseException("unknow field name : " + expr);
  }
 else   if (expr instanceof SQLBinaryOpExpr) {
    return makeField(makeBinaryMethodField((SQLBinaryOpExpr)expr,alias,true),alias,tableAlias);
  }
 else   if (expr instanceof SQLAllColumnExpr) {
  }
 else   if (expr instanceof SQLMethodInvokeExpr) {
    SQLMethodInvokeExpr mExpr=(SQLMethodInvokeExpr)expr;
    String methodName=mExpr.getMethodName();
    if (methodName.equalsIgnoreCase("nested") || methodName.equalsIgnoreCase("reverse_nested")) {
      NestedType nestedType=new NestedType();
      if (nestedType.tryFillFromExpr(mExpr)) {
        return handleIdentifier(nestedType,alias,tableAlias);
      }
    }
 else     if (methodName.equalsIgnoreCase("children")) {
      ChildrenType childrenType=new ChildrenType();
      if (childrenType.tryFillFromExpr(mExpr)) {
        return handleIdentifier(childrenType,alias,tableAlias);
      }
    }
 else     if (methodName.equalsIgnoreCase("filter")) {
      return makeFilterMethodField(mExpr,alias);
    }
    return makeMethodField(methodName,mExpr.getParameters(),null,alias,tableAlias,true);
  }
 else   if (expr instanceof SQLAggregateExpr) {
    SQLAggregateExpr sExpr=(SQLAggregateExpr)expr;
    return makeMethodField(sExpr.getMethodName(),sExpr.getArguments(),sExpr.getOption(),alias,tableAlias,true);
  }
 else   if (expr instanceof SQLCaseExpr) {
    String scriptCode=new CaseWhenParser((SQLCaseExpr)expr,alias,tableAlias).parse();
    List<KVValue> methodParameters=new ArrayList<>();
    if (null != alias && alias.trim().length() != 0) {
      methodParameters.add(new KVValue(alias));
    }
    methodParameters.add(new KVValue(scriptCode));
    return new MethodField("script",methodParameters,null,alias);
  }
 else   if (expr instanceof SQLCastExpr) {
    SQLCastExpr castExpr=(SQLCastExpr)expr;
    if (alias == null) {
      alias="cast_" + castExpr.getExpr().toString();
    }
    String scriptCode=new CastParser(castExpr,alias,tableAlias).parse(true);
    List<KVValue> methodParameters=new ArrayList<>();
    methodParameters.add(new KVValue(alias));
    methodParameters.add(new KVValue(scriptCode));
    return new MethodField("script",methodParameters,null,alias);
  }
 else {
    throw new SqlParseException("unknown field name : " + expr);
  }
  return null;
}
