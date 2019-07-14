private static TypeName[] getTypeNameArray(PsiType[] psiTypes){
  final TypeName[] typeNames=new TypeName[psiTypes.length];
  for (int i=0, size=psiTypes.length; i < size; i++) {
    typeNames[i]=getTypeName(psiTypes[i]);
  }
  return typeNames;
}
