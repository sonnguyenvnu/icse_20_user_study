public MapperFunction lookup(final Class<? extends MapperFunction> mapperFunctionClass){
  return typeCache.get(mapperFunctionClass,() -> {
    try {
      return ClassUtil.newInstance(mapperFunctionClass);
    }
 catch (    Exception ex) {
      throw new IllegalArgumentException("Invalid mapper class " + mapperFunctionClass,ex);
    }
  }
);
}
