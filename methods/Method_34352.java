private Collection<? extends GrantedAuthority> getAuthorities(Map<String,?> map){
  if (!map.containsKey(AUTHORITIES)) {
    return defaultAuthorities;
  }
  Object authorities=map.get(AUTHORITIES);
  if (authorities instanceof String) {
    return AuthorityUtils.commaSeparatedStringToAuthorityList((String)authorities);
  }
  if (authorities instanceof Collection) {
    return AuthorityUtils.commaSeparatedStringToAuthorityList(StringUtils.collectionToCommaDelimitedString((Collection<?>)authorities));
  }
  throw new IllegalArgumentException("Authorities must be either a String or a Collection");
}
