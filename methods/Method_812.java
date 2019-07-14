private static Type getCollectionItemType(ParameterizedType parameterizedType){
  Type rawType=parameterizedType.getRawType();
  Type[] actualTypeArguments=parameterizedType.getActualTypeArguments();
  if (rawType == Collection.class) {
    return getWildcardTypeUpperBounds(actualTypeArguments[0]);
  }
  Class<?> rawClass=(Class<?>)rawType;
  Map<TypeVariable,Type> actualTypeMap=createActualTypeMap(rawClass.getTypeParameters(),actualTypeArguments);
  Type superType=getCollectionSuperType(rawClass);
  if (superType instanceof ParameterizedType) {
    Class<?> superClass=getRawClass(superType);
    Type[] superClassTypeParameters=((ParameterizedType)superType).getActualTypeArguments();
    return superClassTypeParameters.length > 0 ? getCollectionItemType(makeParameterizedType(superClass,superClassTypeParameters,actualTypeMap)) : getCollectionItemType(superClass);
  }
  return getCollectionItemType((Class<?>)superType);
}
