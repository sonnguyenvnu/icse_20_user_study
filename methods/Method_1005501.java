private void map(ClassMap classMap,Object srcObj,Object destObj,boolean bypassSuperMappings,List<String> mappedParentFields,String mapId){
  srcObj=MappingUtils.deProxy(srcObj,beanContainer);
  mappedFields.put(srcObj,destObj,mapId);
  if (classMap == null) {
    classMap=getClassMap(srcObj.getClass(),destObj.getClass(),mapId);
  }
  Class<?> srcClass=srcObj.getClass();
  Class<?> destClass=destObj.getClass();
  Class<?> converterClass=MappingUtils.findCustomConverter(converterByDestTypeCache,classMap.getCustomConverters(),srcClass,destClass);
  if (converterClass != null) {
    destObj=mapUsingCustomConverter(converterClass,srcClass,srcObj,destClass,destObj,null,true);
    return;
  }
  if (!bypassSuperMappings) {
    Collection<ClassMap> superMappings=new ArrayList<>();
    Collection<ClassMap> superClasses=checkForSuperTypeMapping(srcClass,destClass);
    superMappings.addAll(superClasses);
    if (!superMappings.isEmpty()) {
      processSuperTypeMapping(superMappings,srcObj,destObj,mappedParentFields,mapId);
    }
  }
  for (  FieldMap fieldMapping : classMap.getFieldMaps()) {
    String key=MappingUtils.getMappedParentFieldKey(destObj,fieldMapping);
    if (mappedParentFields != null && mappedParentFields.contains(key)) {
      continue;
    }
    mapField(fieldMapping,srcObj,destObj);
  }
}
