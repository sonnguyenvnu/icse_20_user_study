protected Map<String,Object> mapWithAliases(Map<String,Object> source,String alias){
  Map<String,Object> mapWithAliases=new HashMap<>();
  for (  Map.Entry<String,Object> fieldNameToValue : source.entrySet()) {
    if (!aliasesOnReturn.contains(fieldNameToValue.getKey()))     mapWithAliases.put(alias + "." + fieldNameToValue.getKey(),fieldNameToValue.getValue());
 else     mapWithAliases.put(fieldNameToValue.getKey(),fieldNameToValue.getValue());
  }
  return mapWithAliases;
}
