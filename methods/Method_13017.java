protected static Map<String,Collection> matchTypeEntriesWithChar(char c,Map<String,Collection> index){
  if ((c == '*') || (c == '?')) {
    return index;
  }
 else {
    Map<String,Collection> map=new HashMap<>();
    for (    String typeName : index.keySet()) {
      int lastPackageSeparatorIndex=typeName.lastIndexOf('/') + 1;
      int lastTypeNameSeparatorIndex=typeName.lastIndexOf('$') + 1;
      int lastIndex=Math.max(lastPackageSeparatorIndex,lastTypeNameSeparatorIndex);
      if ((lastIndex < typeName.length()) && (typeName.charAt(lastIndex) == c)) {
        map.put(typeName,index.get(typeName));
      }
    }
    return map;
  }
}
