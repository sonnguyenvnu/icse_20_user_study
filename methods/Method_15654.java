@Override public AuthenticationBuilder role(List<Role> role){
  authentication.setRoles(role);
  return this;
}
