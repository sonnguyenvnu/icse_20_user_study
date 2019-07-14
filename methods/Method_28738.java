private String convertHost(String host){
  if (host.equals("127.0.0.1"))   return LOCALHOST_STR;
 else   if (host.equals("::1"))   return LOCALHOST_STR;
  return host;
}
