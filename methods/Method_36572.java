private <T>Set<T> getClassesByServiceLoader(Class<T> clazz){
  ServiceLoader<T> serviceLoader=ServiceLoader.load(clazz);
  Set<T> result=new HashSet<>();
  for (  T t : serviceLoader) {
    result.add(t);
  }
  return result;
}
