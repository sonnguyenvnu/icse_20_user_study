@Override public String getDefaultURL(){
  return PROTOCOL + "httpbin.org/basic-auth/" + SECRET_USERNAME + "/" + SECRET_PASSWORD;
}
