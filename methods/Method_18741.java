static List<TypeVariableName> getTypeVariables(PsiMethod method){
  final List<TypeVariableName> typeVariables=new ArrayList<>();
  for (  PsiTypeParameter psiTypeParameter : method.getTypeParameters()) {
    typeVariables.add(TypeVariableName.get(psiTypeParameter.getName(),(TypeName[])null));
  }
  return typeVariables;
}
