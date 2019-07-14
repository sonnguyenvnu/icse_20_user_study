public Map<String,?> convertUserAuthentication(Authentication authentication){
  Map<String,Object> response=new LinkedHashMap<String,Object>();
  response.put(USERNAME,authentication.getName());
  if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
    response.put(AUTHORITIES,AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
  }
  return response;
}
