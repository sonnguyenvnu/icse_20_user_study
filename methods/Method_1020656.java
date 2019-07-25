public static List<GrantType> list(){
  List<GrantType> grantTypes=new ArrayList<GrantType>();
  GrantType client=new GrantType("client","?????");
  grantTypes.add(client);
  GrantType password=new GrantType("password","???????");
  grantTypes.add(password);
  GrantType refreshToken=new GrantType("refresh_token","????");
  grantTypes.add(refreshToken);
  GrantType authorizationCode=new GrantType("authorization_code","????");
  grantTypes.add(authorizationCode);
  return grantTypes;
}
