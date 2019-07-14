private static int findTypeArgInList(Parameterizable hasTypeParams,String typeArgName){
  List<? extends TypeParameterElement> typeParameters=hasTypeParams.getTypeParameters();
  for (int i=0; i < typeParameters.size(); i++) {
    if (typeParameters.get(i).getSimpleName().contentEquals(typeArgName)) {
      return i;
    }
  }
  return -1;
}
