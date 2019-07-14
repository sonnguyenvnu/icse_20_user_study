public static Class<?> getClass(Type type){
  if (type.getClass() == Class.class) {
    return (Class<?>)type;
  }
  if (type instanceof ParameterizedType) {
    return getClass(((ParameterizedType)type).getRawType());
  }
  if (type instanceof TypeVariable) {
    Type boundType=((TypeVariable<?>)type).getBounds()[0];
    if (boundType instanceof Class) {
      return (Class)boundType;
    }
    return getClass(boundType);
  }
  if (type instanceof WildcardType) {
    Type[] upperBounds=((WildcardType)type).getUpperBounds();
    if (upperBounds.length == 1) {
      return getClass(upperBounds[0]);
    }
  }
  return Object.class;
}
