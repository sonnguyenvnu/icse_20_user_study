public ClassMap find(Class<?> srcClass,Class<?> destClass){
  return classMappings.get(keyFactory.createKey(srcClass,destClass));
}
