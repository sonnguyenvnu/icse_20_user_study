private boolean isStandardPort(String scheme,int port){
  return (scheme.equals("http") && port == 80) || (scheme.equals("https") && port == 443);
}
