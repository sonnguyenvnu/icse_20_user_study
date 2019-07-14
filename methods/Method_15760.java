@Override public GeneratedToken generate(Authentication authentication){
  String token=createToken();
  String userId=authentication.getUser().getId();
  String subject=JSON.toJSONString(new JwtAuthorizedToken(token,userId));
  String jwtToken=createJWT(jwtConfig.getId(),subject,jwtConfig.getTtl());
  int timeout=jwtConfig.getTtl();
  return new GeneratedToken(){
    @Override public Map<String,Object> getResponse(){
      Map<String,Object> map=new HashMap<>();
      map.put("token",jwtToken);
      return map;
    }
    @Override public String getToken(){
      return token;
    }
    @Override public String getType(){
      return TOKEN_TYPE;
    }
    @Override public int getTimeout(){
      return timeout;
    }
  }
;
}
