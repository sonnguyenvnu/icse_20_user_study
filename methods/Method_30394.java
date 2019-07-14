/** 
 * Whether an uri is likely to be handled by native in Frodo. We don't need Frodo's WebActivity.
 */
private static boolean isUriHandled(Uri uri){
  String scheme=uri.getScheme();
  if (TextUtils.equals(scheme,FRODO_SCHEME)) {
    return true;
  }
 else   if (MoreTextUtils.equalsAny(scheme,"https","http")) {
    String host=uri.getHost();
    if (!TextUtils.isEmpty(host) && MoreTextUtils.equalsAny(host,"www.douban.com","m.douban.com","book.douban.com","movie.douban.com","music.douban.com","dongxi.douban.com")) {
      List<String> pathSegments=uri.getPathSegments();
      String pathSegment0=pathSegments.size() >= 1 ? pathSegments.get(0) : null;
      if (!TextUtils.isEmpty(pathSegment0)) {
        if (MoreTextUtils.equalsAny(pathSegment0,"group","theme","update","hashtag","app_topic","subject","book","music","movie","game","mobileapp","event","note","show","doulist","review","photos","celebrity")) {
          return true;
        }
 else         if (TextUtils.equals(pathSegment0,"people")) {
          String pathSegment2=pathSegments.size() >= 3 ? pathSegments.get(2) : null;
          if (TextUtils.isEmpty(pathSegment2)) {
            return true;
          }
 else           if (MoreTextUtils.equalsAny(pathSegment2,"reviews","wish","collect","games","apps","status")) {
            return true;
          }
        }
      }
    }
  }
  return false;
}
