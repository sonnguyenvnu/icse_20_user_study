public static void extract(List<TypeToken<?>> types,Registry registry,Object[] services,int startIndex){
  for (int i=0; i < types.size(); ++i) {
    TypeToken<?> type=types.get(i);
    if (type.getRawType().equals(Optional.class)) {
      TypeToken<?> paramType;
      try {
        paramType=type.resolveType(Optional.class.getMethod("get").getGenericReturnType());
      }
 catch (      NoSuchMethodException e) {
        throw new InternalError("Optional class does not have get method");
      }
      Object optional=registry.maybeGet(paramType);
      services[i + startIndex]=optional;
    }
 else {
      Object service=registry.get(type);
      services[i + startIndex]=service;
    }
  }
}
