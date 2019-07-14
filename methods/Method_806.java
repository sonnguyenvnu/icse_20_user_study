public static Type getGenericParamType(Type type){
  if (type instanceof ParameterizedType) {
    return type;
  }
  if (type instanceof Class) {
    return getGenericParamType(((Class<?>)type).getGenericSuperclass());
  }
  return type;
}
