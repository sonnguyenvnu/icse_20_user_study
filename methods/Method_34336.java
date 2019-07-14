public OAuth2Authentication extractAuthentication(Map<String,?> map){
  Map<String,String> parameters=new HashMap<String,String>();
  Set<String> scope=extractScope(map);
  Authentication user=userTokenConverter.extractAuthentication(map);
  String clientId=(String)map.get(clientIdAttribute);
  parameters.put(clientIdAttribute,clientId);
  if (includeGrantType && map.containsKey(GRANT_TYPE)) {
    parameters.put(GRANT_TYPE,(String)map.get(GRANT_TYPE));
  }
  Set<String> resourceIds=new LinkedHashSet<String>(map.containsKey(AUD) ? getAudience(map) : Collections.<String>emptySet());
  Collection<? extends GrantedAuthority> authorities=null;
  if (user == null && map.containsKey(AUTHORITIES)) {
    @SuppressWarnings("unchecked") String[] roles=((Collection<String>)map.get(AUTHORITIES)).toArray(new String[0]);
    authorities=AuthorityUtils.createAuthorityList(roles);
  }
  OAuth2Request request=new OAuth2Request(parameters,clientId,authorities,true,scope,resourceIds,null,null,null);
  return new OAuth2Authentication(request,user);
}
