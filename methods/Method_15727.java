@Override public boolean handle(DataAccessConfig access,AuthorizingContext context){
  FieldFilterDataAccessConfig filterDataAccessConfig=((FieldFilterDataAccessConfig)access);
switch (access.getAction()) {
case Permission.ACTION_QUERY:
case Permission.ACTION_GET:
    return doQueryAccess(filterDataAccessConfig,context);
case Permission.ACTION_ADD:
case Permission.ACTION_UPDATE:
  return doUpdateAccess(filterDataAccessConfig,context);
default :
if (logger.isDebugEnabled()) {
  logger.debug("field filter not support for {}",access.getAction());
}
return true;
}
}
