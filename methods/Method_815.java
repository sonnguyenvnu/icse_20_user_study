private static Type getActualType(Type typeParameter,Map<TypeVariable,Type> actualTypeMap){
  if (typeParameter instanceof TypeVariable) {
    return actualTypeMap.get(typeParameter);
  }
 else   if (typeParameter instanceof ParameterizedType) {
    return makeParameterizedType(getRawClass(typeParameter),((ParameterizedType)typeParameter).getActualTypeArguments(),actualTypeMap);
  }
 else   if (typeParameter instanceof GenericArrayType) {
    return new GenericArrayTypeImpl(getActualType(((GenericArrayType)typeParameter).getGenericComponentType(),actualTypeMap));
  }
  return typeParameter;
}
