public boolean contains(Class<?> srcClass,Class<?> destClass,String mapId){
  String key=keyFactory.createKey(srcClass,destClass,mapId);
  return classMappings.containsKey(key);
}
