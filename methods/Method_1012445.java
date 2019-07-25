@Override public List<RoleVO> tree(String tenantCode){
  String userRole=SecureUtil.getUserRole();
  String excludeRole=null;
  if (!CollectionUtil.contains(Func.toStrArray(userRole),RoleConstant.ADMIN)) {
    excludeRole=RoleConstant.ADMIN;
  }
  return ForestNodeMerger.merge(baseMapper.tree(tenantCode,excludeRole));
}
