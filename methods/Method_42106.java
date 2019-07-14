public static Observable<Album> deleteAlbum(Context context,Album album){
  return Observable.create(subscriber -> {
    ArrayList<Observable<Media>> sources=new ArrayList<>(album.getCount());
    CPHelper.getMedia(context,album).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(media -> sources.add(MediaHelper.deleteMedia(context.getApplicationContext(),media)),subscriber::onError,() -> Observable.mergeDelayError(sources).observeOn(AndroidSchedulers.mainThread(),true).subscribeOn(Schedulers.newThread()).subscribe(item -> {
    }
,subscriber::onError,() -> {
      subscriber.onNext(album);
      subscriber.onComplete();
    }
));
  }
);
}
