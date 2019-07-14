@SuppressWarnings("unchecked") protected static void match(char c,Map<String,Collection> index,Map<String,Collection> result){
  if (Character.isLowerCase(c)) {
    char upperCase=Character.toUpperCase(c);
    for (    Map.Entry<String,Collection> mapEntry : index.entrySet()) {
      String typeName=mapEntry.getKey();
      Collection<Container.Entry> entries=mapEntry.getValue();
      int lastPackageSeparatorIndex=typeName.lastIndexOf('/') + 1;
      int lastTypeNameSeparatorIndex=typeName.lastIndexOf('$') + 1;
      int lastIndex=Math.max(lastPackageSeparatorIndex,lastTypeNameSeparatorIndex);
      if (lastIndex < typeName.length()) {
        char first=typeName.charAt(lastIndex);
        if ((first == c) || (first == upperCase)) {
          add(result,typeName,entries);
        }
      }
    }
  }
 else {
    for (    Map.Entry<String,Collection> mapEntry : index.entrySet()) {
      String typeName=mapEntry.getKey();
      Collection<Container.Entry> entries=mapEntry.getValue();
      int lastPackageSeparatorIndex=typeName.lastIndexOf('/') + 1;
      int lastTypeNameSeparatorIndex=typeName.lastIndexOf('$') + 1;
      int lastIndex=Math.max(lastPackageSeparatorIndex,lastTypeNameSeparatorIndex);
      if ((lastIndex < typeName.length()) && (typeName.charAt(lastIndex) == c)) {
        add(result,typeName,entries);
      }
    }
  }
}
