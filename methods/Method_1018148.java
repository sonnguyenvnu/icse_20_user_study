@Override public Set<String> grant(Principal principal){
  if (principal instanceof Group) {
    Set<String> authorities=new HashSet<>();
    addAuthorities(principal,authorities);
    return authorities;
  }
 else {
    return null;
  }
}
