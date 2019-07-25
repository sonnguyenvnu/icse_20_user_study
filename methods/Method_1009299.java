@Nullable private static List<String> down(@NotNull PsiElement qualifier){
  List<String> nameList=null;
  if (qualifier instanceof Call) {
    nameList=down((Call)qualifier);
  }
 else   if (qualifier instanceof ElixirAccessExpression) {
    nameList=down(qualifier.getChildren());
  }
 else   if (qualifier instanceof QualifiableAlias) {
    nameList=down((QualifiableAlias)qualifier);
  }
  return nameList;
}
