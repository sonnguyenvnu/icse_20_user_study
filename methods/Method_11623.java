public static String getDomain(String url){
  String domain=removeProtocol(url);
  int i=StringUtils.indexOf(domain,"/",1);
  if (i > 0) {
    domain=StringUtils.substring(domain,0,i);
  }
  return removePort(domain);
}
