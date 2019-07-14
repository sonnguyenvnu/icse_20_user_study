protected TypeJsonSerializer _lookup(final Class type){
synchronized (map) {
    TypeJsonSerializer tjs=lookupSerializer(type);
    if (tjs != null) {
      return tjs;
    }
    ClassDescriptor cd=ClassIntrospector.get().lookup(type);
    if (cd.isArray()) {
      return lookupSerializer(Arrays.class);
    }
    Class[] interfaces=cd.getAllInterfaces();
    for (    Class interfaze : interfaces) {
      tjs=lookupSerializer(interfaze);
      if (tjs != null) {
        return tjs;
      }
    }
    Class[] superclasses=cd.getAllSuperclasses();
    for (    Class clazz : superclasses) {
      tjs=lookupSerializer(clazz);
      if (tjs != null) {
        return tjs;
      }
    }
    return lookupSerializer(Object.class);
  }
}
