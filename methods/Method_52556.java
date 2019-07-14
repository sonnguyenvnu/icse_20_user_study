private static Set<Class<?>> getErasedSuperTypeSet(Class<?> clazz,Set<Class<?>> destinationSet){
  if (clazz != null) {
    destinationSet.add(clazz);
    getErasedSuperTypeSet(clazz.getSuperclass(),destinationSet);
    for (    Class<?> interfaceType : clazz.getInterfaces()) {
      getErasedSuperTypeSet(interfaceType,destinationSet);
    }
  }
  return destinationSet;
}
