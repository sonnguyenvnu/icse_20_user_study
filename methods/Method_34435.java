public OAuth2Request createOAuth2Request(ClientDetails client){
  Map<String,String> requestParameters=getRequestParameters();
  HashMap<String,String> modifiable=new HashMap<String,String>(requestParameters);
  modifiable.remove("password");
  modifiable.remove("client_secret");
  modifiable.put("grant_type",grantType);
  return new OAuth2Request(modifiable,client.getClientId(),client.getAuthorities(),true,this.getScope(),client.getResourceIds(),null,null,null);
}
