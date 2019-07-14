public static Class<?> getCollectionItemClass(Type fieldType){
  if (fieldType instanceof ParameterizedType) {
    Class<?> itemClass;
    Type actualTypeArgument=((ParameterizedType)fieldType).getActualTypeArguments()[0];
    if (actualTypeArgument instanceof WildcardType) {
      WildcardType wildcardType=(WildcardType)actualTypeArgument;
      Type[] upperBounds=wildcardType.getUpperBounds();
      if (upperBounds.length == 1) {
        actualTypeArgument=upperBounds[0];
      }
    }
    if (actualTypeArgument instanceof Class) {
      itemClass=(Class<?>)actualTypeArgument;
      if (!Modifier.isPublic(itemClass.getModifiers())) {
        throw new JSONException("can not create ASMParser");
      }
    }
 else {
      throw new JSONException("can not create ASMParser");
    }
    return itemClass;
  }
  return Object.class;
}
