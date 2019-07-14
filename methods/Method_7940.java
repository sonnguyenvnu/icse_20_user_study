private void resetUrlPaths(boolean text){
  if (text) {
    if (urlPathSelection.isEmpty()) {
      return;
    }
    urlPathCache.addAll(urlPathSelection);
    urlPathSelection.clear();
  }
 else {
    if (urlPath.isEmpty()) {
      return;
    }
    urlPathCache.addAll(urlPath);
    urlPath.clear();
  }
}
