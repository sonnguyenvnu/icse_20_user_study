@Override public boolean handle(DataAccessConfig access,AuthorizingContext context){
  FieldScopeDataAccessConfig own=((FieldScopeDataAccessConfig)access);
  Object controller=context.getParamContext().getTarget();
  if (controller != null) {
switch (access.getAction()) {
case Permission.ACTION_QUERY:
case Permission.ACTION_GET:
      return doQueryAccess(own,context);
case Permission.ACTION_DELETE:
case Permission.ACTION_UPDATE:
    return doRWAccess(own,context,controller);
case Permission.ACTION_ADD:
default :
  logger.warn("action: {} not support now!",access.getAction());
}
}
 else {
logger.warn("target is null!");
}
return true;
}
