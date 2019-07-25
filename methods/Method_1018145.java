@Override public boolean disable(Principal principal,Set<Action> actions){
  throw new UnsupportedOperationException("Changing permissions is not supported by this type of " + AllowedActions.class.getSimpleName());
}
