private static Class<?> getFirstGenericParameter(final Type type,final int nestedDepth){
  int cDepth=0;
  Type tType=type;
  for (int cDept=0; cDept < nestedDepth; cDept++) {
    if (!(tType instanceof ParameterizedType))     throw new IllegalStateException(String.format("Sub type at nesting level %d of %s is expected to be generic",cDepth,type));
    tType=((ParameterizedType)tType).getActualTypeArguments()[cDept];
  }
  if (tType instanceof ParameterizedType)   return (Class<?>)((ParameterizedType)tType).getRawType();
 else   if (tType instanceof Class)   return (Class<?>)tType;
  throw new UnsupportedOperationException("Unsupported type " + tType);
}
