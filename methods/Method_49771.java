public boolean isExcluded(String url){
  if (TextUtils.isEmpty(url) || mParsedExclusionList == null || mParsedExclusionList.length == 0)   return false;
  Uri u=Uri.parse(url);
  String urlDomain=u.getHost();
  if (urlDomain == null)   return false;
  for (int i=0; i < mParsedExclusionList.length; i+=2) {
    if (urlDomain.equals(mParsedExclusionList[i]) || urlDomain.endsWith(mParsedExclusionList[i + 1])) {
      return true;
    }
  }
  return false;
}
