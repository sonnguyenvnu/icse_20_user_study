@Override protected Set<String> getTryOperationScope(String scopeType,PersonnelAuthentication authorization){
switch (scopeType) {
case SCOPE_TYPE_CHILDREN:
    return authorization.getAllPositionId();
case SCOPE_TYPE_ONLY_SELF:
  return authorization.getRootPositionId();
default :
return new java.util.HashSet<>();
}
}
