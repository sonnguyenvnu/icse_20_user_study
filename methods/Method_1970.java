private void loadUrls(){
  if (mUrlsLocal) {
    if (!mHasStoragePermissions) {
      mRequestedLocalSource=true;
      requestStoragePermissions();
      return;
    }
    loadLocalUrls();
  }
 else {
    mRequestedLocalSource=false;
    loadNetworkUrls();
  }
}
