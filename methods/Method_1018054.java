@Override public boolean disable(Principal principal,Set<Action> actions){
  if (!principal.equals(this.category.getOwner())) {
    boolean changed=super.disable(principal,actions);
    updateEntityAccess(principal,getEnabledActions(principal));
    return changed;
  }
 else {
    return false;
  }
}
