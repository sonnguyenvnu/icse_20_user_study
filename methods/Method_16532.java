@Override public boolean handle(DataAccessConfig access,AuthorizingContext context){
  ScopeByUserDataAccessConfig scope=((ScopeByUserDataAccessConfig)access);
switch (access.getAction()) {
case Permission.ACTION_QUERY:
case Permission.ACTION_GET:
    return doQueryAccess(scope,context);
case Permission.ACTION_ADD:
case Permission.ACTION_UPDATE:
default :
  return doUpdateAccess(scope,context);
}
}
