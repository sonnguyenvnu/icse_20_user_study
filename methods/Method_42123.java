private static Observable<Album> getHiddenAlbums(Context context,ArrayList<String> excludedAlbums){
  boolean includeVideo=Prefs.showVideos();
  return Observable.create(subscriber -> {
    try {
      ArrayList<String> lastHidden=Hawk.get("h",new ArrayList<>());
      for (      String s : lastHidden)       checkAndAddFolder(new File(s),subscriber,includeVideo);
      lastHidden.addAll(excludedAlbums);
      for (      File storage : StorageHelper.getStorageRoots(context))       fetchRecursivelyHiddenFolder(storage,subscriber,lastHidden,includeVideo);
      subscriber.onComplete();
    }
 catch (    Exception err) {
      subscriber.onError(err);
    }
  }
);
}
