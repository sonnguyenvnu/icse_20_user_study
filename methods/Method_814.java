private static Map<TypeVariable,Type> createActualTypeMap(TypeVariable[] typeParameters,Type[] actualTypeArguments){
  int length=typeParameters.length;
  Map<TypeVariable,Type> actualTypeMap=new HashMap<TypeVariable,Type>(length);
  for (int i=0; i < length; i++) {
    actualTypeMap.put(typeParameters[i],actualTypeArguments[i]);
  }
  return actualTypeMap;
}
