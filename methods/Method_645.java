static Map<String,String> createAliasMap(String... aliasList){
  Map<String,String> aliasMap=new HashMap<String,String>();
  for (  String alias : aliasList) {
    aliasMap.put(alias,alias);
  }
  return aliasMap;
}
