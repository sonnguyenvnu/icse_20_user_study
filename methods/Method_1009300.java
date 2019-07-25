@Nullable private static List<String> down(@NotNull PsiElement[] qualifiers){
  List<String> nameList=null;
  for (  PsiElement qualifier : qualifiers) {
    List<String> qualifierNameList=down(qualifier);
    if (qualifierNameList != null) {
      if (nameList == null) {
        nameList=new ArrayList<String>(qualifierNameList.size());
      }
      nameList.addAll(qualifierNameList);
    }
  }
  return nameList;
}
