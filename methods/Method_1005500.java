public void map(final Object srcObj,final Object destObj,final String mapId){
  MappingValidator.validateMappingRequest(srcObj,destObj);
  mapGeneral(srcObj,null,destObj,mapId);
}
