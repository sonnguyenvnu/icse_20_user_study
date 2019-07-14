@Override public void disable(String roleId){
  tryValidateProperty(StringUtils.hasLength(roleId),RoleEntity.id,"{id_is_null}");
  DefaultDSLUpdateService.createUpdate(getDao()).set(RoleEntity.status,DataStatus.STATUS_DISABLED).where(RoleEntity.id,roleId).exec();
}
