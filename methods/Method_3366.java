static String getAlias(Argument argument){
  String alias=argument.alias();
  if (alias.equals("")) {
    alias=null;
  }
  return alias;
}
