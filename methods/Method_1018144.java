@Override public boolean disable(Principal principal,Action action,Action... more){
  throw new UnsupportedOperationException("Changing permissions is not supported by this type of " + AllowedActions.class.getSimpleName());
}
