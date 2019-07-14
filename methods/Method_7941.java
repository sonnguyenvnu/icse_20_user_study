private LinkPath obtainNewUrlPath(boolean text){
  LinkPath linkPath;
  if (!urlPathCache.isEmpty()) {
    linkPath=urlPathCache.get(0);
    urlPathCache.remove(0);
  }
 else {
    linkPath=new LinkPath();
  }
  linkPath.reset();
  if (text) {
    urlPathSelection.add(linkPath);
  }
 else {
    urlPath.add(linkPath);
  }
  return linkPath;
}
