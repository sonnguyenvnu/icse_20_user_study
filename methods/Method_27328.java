public static boolean isEnterprise(@Nullable String url){
  if (InputHelper.isEmpty(url) || !PrefGetter.isEnterprise())   return false;
  String enterpriseUrl=PrefGetter.getEnterpriseUrl().toLowerCase();
  url=url.toLowerCase();
  return url.equalsIgnoreCase(enterpriseUrl) || url.startsWith(enterpriseUrl) || url.startsWith(getEndpoint(enterpriseUrl)) || url.contains(enterpriseUrl) || enterpriseUrl.contains(url);
}
