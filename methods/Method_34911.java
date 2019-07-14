private static String getFullDomainFromUrl(String url){
  int pos1=url.indexOf("//") + 2;
  int pos2=url.indexOf("/",pos1);
  String path=url.substring(0,pos2);
  return path;
}
