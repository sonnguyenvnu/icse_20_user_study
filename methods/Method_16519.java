@Override protected Set<String> getTryOperationScope(String scopeType,PersonnelAuthentication authorization){
switch (scopeType) {
case SCOPE_TYPE_CHILDREN:
    return authorization.getAllOrgId();
case SCOPE_TYPE_ONLY_SELF:
  return authorization.getRootOrgId();
default :
return new java.util.HashSet<>();
}
}
