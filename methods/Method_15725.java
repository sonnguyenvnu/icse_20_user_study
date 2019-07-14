@Override public boolean doAccess(DataAccessConfig access,AuthorizingContext context){
  if (parent != null) {
    parent.doAccess(access,context);
  }
  return handlers.stream().filter(handler -> handler.isSupport(access)).allMatch(handler -> handler.handle(access,context));
}
