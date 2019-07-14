@Override public boolean isUser(){
  Authentication authentication=getUserAuthentication();
  return authentication != null;
}
