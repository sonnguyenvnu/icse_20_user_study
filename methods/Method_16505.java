@Override public boolean handle(DataAccessConfig access,AuthorizingContext context){
  return ((SimpleCustomScopeDataAccessConfig)access).getScope().stream().map(scope -> new SimpleScopeDataAccessConfig(scope.getType(),DataAccessType.SCOPE_TYPE_CUSTOM,access.getAction(),new HashSet<>(scope.getIds()))).allMatch(accessConfig -> handlers.stream().filter(handler -> handler.isSupport(accessConfig)).allMatch(handler -> handler.handle(accessConfig,context)));
}
