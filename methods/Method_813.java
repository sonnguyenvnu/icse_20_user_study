private static Type getCollectionSuperType(Class<?> clazz){
  Type assignable=null;
  for (  Type type : clazz.getGenericInterfaces()) {
    Class<?> rawClass=getRawClass(type);
    if (rawClass == Collection.class) {
      return type;
    }
    if (Collection.class.isAssignableFrom(rawClass)) {
      assignable=type;
    }
  }
  return assignable == null ? clazz.getGenericSuperclass() : assignable;
}
