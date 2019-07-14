@SuppressWarnings("unchecked") protected static void match(Pattern regExpPattern,Map<String,Collection> index,Map<String,Collection> result){
  for (  Map.Entry<String,Collection> mapEntry : index.entrySet()) {
    String typeName=mapEntry.getKey();
    Collection<Container.Entry> entries=mapEntry.getValue();
    int lastPackageSeparatorIndex=typeName.lastIndexOf('/') + 1;
    int lastTypeNameSeparatorIndex=typeName.lastIndexOf('$') + 1;
    int lastIndex=Math.max(lastPackageSeparatorIndex,lastTypeNameSeparatorIndex);
    if (regExpPattern.matcher(typeName.substring(lastIndex)).matches()) {
      add(result,typeName,entries);
    }
  }
}
