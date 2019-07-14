public static Class<?> getRawClass(Type type){
  if (type instanceof Class<?>) {
    return (Class<?>)type;
  }
 else   if (type instanceof ParameterizedType) {
    return getRawClass(((ParameterizedType)type).getRawType());
  }
 else {
    throw new JSONException("TODO");
  }
}
