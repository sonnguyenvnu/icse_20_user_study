public static ImmutableList<TypeVariableName> getTypeVariables(PsiClass psiClass){
  PsiTypeParameter[] psiTypeParameters=psiClass.getTypeParameters();
  final List<TypeVariableName> typeVariables=new ArrayList<>(psiTypeParameters.length);
  for (  PsiTypeParameter psiTypeParameter : psiTypeParameters) {
    final PsiReferenceList extendsList=psiTypeParameter.getExtendsList();
    final PsiClassType[] psiClassTypes=extendsList.getReferencedTypes();
    final TypeName[] boundsTypeNames=new TypeName[psiClassTypes.length];
    for (int i=0, size=psiClassTypes.length; i < size; i++) {
      boundsTypeNames[i]=PsiTypeUtils.getTypeName(psiClassTypes[i]);
    }
    final TypeVariableName typeVariable=TypeVariableName.get(psiTypeParameter.getName(),boundsTypeNames);
    typeVariables.add(typeVariable);
  }
  return ImmutableList.copyOf(typeVariables);
}
