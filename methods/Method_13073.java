@Override public boolean openUri(URI uri){
  try {
    pageChangedListenersEnabled=false;
    T page=showPage(uri);
    if (page != null) {
      if (page instanceof UriOpenable) {
        pageChangedListenersEnabled=true;
        return ((UriOpenable)page).openUri(uri);
      }
      return true;
    }
  }
  finally {
    pageChangedListenersEnabled=true;
  }
  return false;
}
