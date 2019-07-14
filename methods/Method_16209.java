@Override @CacheEvict(allEntries=true) public void enable(String id){
  tryValidateProperty(StringUtils.hasLength(id),MenuGroupEntity.id,"{id_is_null}");
  createUpdate().set(MenuGroupEntity.status,1).where(MenuGroupEntity.id,id).exec();
}
