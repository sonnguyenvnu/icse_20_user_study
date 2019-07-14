@Override public void enable(String roleId){
  tryValidateProperty(StringUtils.hasLength(roleId),RoleEntity.id,"{id_is_null}");
  DefaultDSLUpdateService.createUpdate(getDao()).set(RoleEntity.status,DataStatus.STATUS_ENABLED).where(RoleEntity.id,roleId).exec();
}
