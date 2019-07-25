@Nullable private static List<String> down(@NotNull QualifiableAlias qualifier){
  String resolvableName=qualifier.getName();
  List<String> nameList=null;
  if (resolvableName != null) {
    nameList=new ArrayList<String>();
    nameList.add(resolvableName);
  }
  return nameList;
}
