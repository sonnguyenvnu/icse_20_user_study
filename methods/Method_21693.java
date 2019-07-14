private String gettAggNameFromParamsOrAlias(MethodField field){
  String alias=field.getAlias();
  for (  KVValue kv : field.getParams()) {
    if (kv.key != null && kv.key.equals("alias"))     alias=kv.value.toString();
  }
  return alias;
}
