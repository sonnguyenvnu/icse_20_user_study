@Override public boolean disable(Principal principal,AllowedActions actions){
  throw new UnsupportedOperationException("Changing permissions is not supported by this type of " + AllowedActions.class.getSimpleName());
}
