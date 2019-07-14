@Override protected Set<String> getTryOperationScope(String scopeType,PersonnelAuthentication authorization){
switch (scopeType) {
case SCOPE_TYPE_CHILDREN:
    return authorization.getAllDistrictId();
case SCOPE_TYPE_ONLY_SELF:
  return authorization.getRootDistrictId();
default :
return new java.util.HashSet<>();
}
}
