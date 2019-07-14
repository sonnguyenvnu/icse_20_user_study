@SuppressWarnings({"unchecked"}) private OAuth2Request getRequest(Map<String,Object> map){
  Map<String,Object> request=(Map<String,Object>)map.get("oauth2Request");
  String clientId=(String)request.get("clientId");
  Set<String> scope=new LinkedHashSet<>(request.containsKey("scope") ? (Collection<String>)request.get("scope") : Collections.<String>emptySet());
  return new OAuth2Request(null,clientId,null,true,new HashSet<>(scope),null,null,null,null);
}
