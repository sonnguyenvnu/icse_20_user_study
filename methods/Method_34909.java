private static String getAbsoluteUrlFrom(String pageUrl,String linkPath){
  String domainUrl=getFullDomainFromUrl(pageUrl);
  if (linkPath.startsWith("/")) {
    return domainUrl + linkPath;
  }
  return domainUrl + "/" + linkPath;
}
