private Type getFirstParametrizedType(Method m){
  Type gtype=m.getGenericReturnType();
  if (gtype instanceof ParameterizedType) {
    ParameterizedType pType=(ParameterizedType)gtype;
    return pType.getActualTypeArguments()[0];
  }
  return null;
}
