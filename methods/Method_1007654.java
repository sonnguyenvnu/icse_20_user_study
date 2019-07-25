@Override public boolean satisfied(InjectedValueAccess context){
  return permissions.isEmpty() || context.injectedValue(ACTOR_KEY).map(actor -> permissions.stream().anyMatch(actor::hasPermission)).orElse(false);
}
