protected Set<String> getTryOperationScope(ScopeDataAccessConfig access){
  if (DataAccessType.SCOPE_TYPE_CUSTOM.equals(access.getScopeType())) {
    return access.getScope().stream().map(String::valueOf).collect(Collectors.toSet());
  }
  return getTryOperationScope(access.getScopeType(),getPersonnelAuthorization());
}
