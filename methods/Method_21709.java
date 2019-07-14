private boolean isSubsetFields(List<Field> bigGroup,List<Field> smallerGroup){
  Set<String> biggerGroup=new HashSet<>();
  for (  Field field : bigGroup) {
    String fieldName=getNameOrAlias(field);
    biggerGroup.add(fieldName);
  }
  for (  Field field : smallerGroup) {
    String fieldName=getNameOrAlias(field);
    if (!biggerGroup.contains(fieldName)) {
      return false;
    }
  }
  return true;
}
