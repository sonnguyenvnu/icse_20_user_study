private static Observable<Media> getMediaFromStorage(Context context,Album album){
  return Observable.create(subscriber -> {
    File dir=new File(album.getPath());
    File[] files=dir.listFiles(new ImageFileFilter(Prefs.showVideos()));
    try {
      if (files != null && files.length > 0)       for (      File file : files)       subscriber.onNext(new Media(file));
      subscriber.onComplete();
    }
 catch (    Exception err) {
      subscriber.onError(err);
    }
  }
);
}
