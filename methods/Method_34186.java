private boolean needsUserAuthorizationUri(String type){
  return type.equals("authorization_code") || type.equals("implicit");
}
