@Override protected void disable(Principal principal){
  enableOnly(principal,streamAllRoleMemberships(),getAllowedActions());
}
