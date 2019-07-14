private boolean isAuthCodeRequest(Map<String,String> parameters){
  return "authorization_code".equals(parameters.get("grant_type")) && parameters.get("code") != null;
}
