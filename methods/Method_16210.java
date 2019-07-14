@Override @CacheEvict(allEntries=true) public void disable(String id){
  tryValidateProperty(StringUtils.hasLength(id),MenuGroupEntity.id,"{id_is_null}");
  DefaultDSLUpdateService.createUpdate(getDao()).set(MenuGroupEntity.status,0).where(MenuGroupEntity.id,id).exec();
}
