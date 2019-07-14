private boolean isRefreshTokenRequest(Map<String,String> parameters){
  return "refresh_token".equals(parameters.get("grant_type")) && parameters.get("refresh_token") != null;
}
