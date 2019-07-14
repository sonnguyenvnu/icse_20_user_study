public static MethodField makeMethodField(String name,List<SQLExpr> arguments,SQLAggregateOption option,String alias,String tableAlias,boolean first) throws SqlParseException {
  List<KVValue> paramers=new LinkedList<>();
  String finalMethodName=name;
  for (  SQLExpr object : arguments) {
    if (object instanceof SQLBinaryOpExpr) {
      SQLBinaryOpExpr binaryOpExpr=(SQLBinaryOpExpr)object;
      if (SQLFunctions.buildInFunctions.contains(binaryOpExpr.getOperator().toString().toLowerCase())) {
        SQLMethodInvokeExpr mExpr=makeBinaryMethodField(binaryOpExpr,alias,first);
        MethodField mf=makeMethodField(mExpr.getMethodName(),mExpr.getParameters(),null,null,tableAlias,false);
        String key=mf.getParams().get(0).toString(), value=mf.getParams().get(1).toString();
        paramers.add(new KVValue(key,new SQLCharExpr(first && !SQLFunctions.buildInFunctions.contains(finalMethodName) ? String.format("%s;return %s;",value,key) : value)));
      }
 else {
        if (!binaryOpExpr.getOperator().getName().equals("=")) {
          paramers.add(new KVValue("script",makeScriptMethodField(binaryOpExpr,null,tableAlias)));
        }
 else {
          SQLExpr right=binaryOpExpr.getRight();
          Object value=Util.expr2Object(right);
          paramers.add(new KVValue(binaryOpExpr.getLeft().toString(),value));
        }
      }
    }
 else     if (object instanceof SQLMethodInvokeExpr) {
      SQLMethodInvokeExpr mExpr=(SQLMethodInvokeExpr)object;
      String methodName=mExpr.getMethodName().toLowerCase();
      if (methodName.equals("script")) {
        KVValue script=new KVValue("script",makeMethodField(mExpr.getMethodName(),mExpr.getParameters(),null,alias,tableAlias,true));
        paramers.add(script);
      }
 else       if (methodName.equals("nested") || methodName.equals("reverse_nested")) {
        NestedType nestedType=new NestedType();
        if (!nestedType.tryFillFromExpr(object)) {
          throw new SqlParseException("failed parsing nested expr " + object);
        }
        paramers.add(new KVValue("nested",nestedType));
      }
 else       if (methodName.equals("children")) {
        ChildrenType childrenType=new ChildrenType();
        if (!childrenType.tryFillFromExpr(object)) {
          throw new SqlParseException("failed parsing children expr " + object);
        }
        paramers.add(new KVValue("children",childrenType));
      }
 else       if (SQLFunctions.buildInFunctions.contains(methodName)) {
        MethodField mf=makeMethodField(methodName,mExpr.getParameters(),null,null,tableAlias,false);
        String key=mf.getParams().get(0).toString(), value=mf.getParams().get(1).toString();
        paramers.add(new KVValue(key,new SQLCharExpr(first && !SQLFunctions.buildInFunctions.contains(finalMethodName) ? String.format("%s;return %s;",value,key) : value)));
      }
 else       throw new SqlParseException("only support script/nested/children as inner functions");
    }
 else     if (object instanceof SQLCaseExpr) {
      String scriptCode=new CaseWhenParser((SQLCaseExpr)object,alias,tableAlias).parse();
      paramers.add(new KVValue("script",new SQLCharExpr(scriptCode)));
    }
 else     if (object instanceof SQLCastExpr) {
      CastParser castParser=new CastParser((SQLCastExpr)object,alias,tableAlias);
      String scriptCode=castParser.parse(false);
      paramers.add(new KVValue(castParser.getName(),new SQLCharExpr(scriptCode)));
    }
 else {
      paramers.add(new KVValue(Util.removeTableAilasFromField(object,tableAlias)));
    }
  }
  if (SQLFunctions.buildInFunctions.contains(finalMethodName.toLowerCase())) {
    if (alias == null && first) {
      alias="field_" + SQLFunctions.random();
    }
    Tuple<String,String> newFunctions=SQLFunctions.function(finalMethodName,paramers,paramers.get(0).key,first);
    paramers.clear();
    if (!first) {
      paramers.add(new KVValue(newFunctions.v1()));
    }
 else {
      if (newFunctions.v1().toLowerCase().contains("if")) {
        paramers.add(new KVValue(newFunctions.v1()));
      }
 else {
        paramers.add(new KVValue(alias));
      }
    }
    paramers.add(new KVValue(newFunctions.v2()));
    finalMethodName="script";
  }
  if (first) {
    List<KVValue> tempParamers=new LinkedList<>();
    for (    KVValue temp : paramers) {
      if (temp.value instanceof SQLExpr)       tempParamers.add(new KVValue(temp.key,Util.expr2Object((SQLExpr)temp.value)));
 else       tempParamers.add(new KVValue(temp.key,temp.value));
    }
    paramers.clear();
    paramers.addAll(tempParamers);
  }
  return new MethodField(finalMethodName,paramers,option == null ? null : option.name(),alias);
}
