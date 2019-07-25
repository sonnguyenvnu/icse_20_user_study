@Override public Optional<TypeInfo> find(String typename){
  try {
    return Optional.of(classLoader.loadClass(typename)).map(RuntimeTypeInfo::new);
  }
 catch (  ClassNotFoundException e) {
    return Optional.empty();
  }
}
