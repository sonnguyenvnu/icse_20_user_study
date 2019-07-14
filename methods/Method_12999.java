@Override @SuppressWarnings("unchecked") public boolean openURI(URI uri){
  if (uri != null) {
    boolean success=mainView.openUri(uri);
    if (success == false) {
      UriLoader uriLoader=getUriLoader(uri);
      if (uriLoader != null) {
        success=uriLoader.load(this,uri);
      }
    }
    if (success) {
      addURI(uri);
    }
    return success;
  }
  return false;
}
