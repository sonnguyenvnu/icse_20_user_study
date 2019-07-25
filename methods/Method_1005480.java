public ClassMap find(Class<?> srcClass,Class<?> destClass,String mapId){
  final String key=keyFactory.createKey(srcClass,destClass,mapId);
  ClassMap mapping=classMappings.get(key);
  if (mapping == null) {
    mapping=findInterfaceMapping(destClass,srcClass,mapId);
    if (mapping != null) {
      ClassMap previous=classMappings.putIfAbsent(keyFactory.createKey(srcClass,destClass,mapId),mapping);
      if (previous != null) {
        mapping=previous;
      }
    }
  }
  if (!MappingUtils.isBlankOrNull(mapId) && mapping == null) {
    for (    Entry<String,ClassMap> entry : classMappings.entrySet()) {
      ClassMap classMap=entry.getValue();
      if (StringUtils.equals(classMap.getMapId(),mapId) && classMap.getSrcClassToMap().isAssignableFrom(srcClass) && classMap.getDestClassToMap().isAssignableFrom(destClass)) {
        return classMap;
      }
 else       if (StringUtils.equals(classMap.getMapId(),mapId) && srcClass.equals(destClass)) {
        return classMap;
      }
    }
    MappingUtils.throwMappingException("Class mapping not found by map-id: " + key);
  }
  return mapping;
}
