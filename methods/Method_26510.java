private static String describeVariable(VarSymbol symbol){
switch (symbol.getKind()) {
case FIELD:
    return "field";
case LOCAL_VARIABLE:
  return "local variable";
case PARAMETER:
return "parameter";
default :
return "variable";
}
}
