@Override public Set<String> grant(Principal principal){
  if (principal instanceof UsernamePrincipal) {
    String name=principal.getName();
    return Sets.newHashSet(name);
  }
 else {
    return null;
  }
}
