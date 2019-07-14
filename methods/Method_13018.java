protected static Map<String,Collection> matchTypeEntriesWithString(String pattern,Map<String,Collection> index){
  Pattern p=createPattern(pattern);
  Map<String,Collection> map=new HashMap<>();
  for (  String typeName : index.keySet()) {
    int lastPackageSeparatorIndex=typeName.lastIndexOf('/') + 1;
    int lastTypeNameSeparatorIndex=typeName.lastIndexOf('$') + 1;
    int lastIndex=Math.max(lastPackageSeparatorIndex,lastTypeNameSeparatorIndex);
    if (p.matcher(typeName.substring(lastIndex)).matches()) {
      map.put(typeName,index.get(typeName));
    }
  }
  return map;
}
