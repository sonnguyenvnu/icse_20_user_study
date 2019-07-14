@Override public void generateMeidas(final String bucketId,final int page,final int limit){
  Observable.create((ObservableOnSubscribe<List<MediaBean>>)subscriber -> {
    List<MediaBean> mediaBeanList=null;
    if (isImage) {
      mediaBeanList=MediaUtils.getMediaWithImageList(context,bucketId,page,limit);
    }
 else {
      mediaBeanList=MediaUtils.getMediaWithVideoList(context,bucketId,page,limit);
    }
    subscriber.onNext(mediaBeanList);
    subscriber.onComplete();
  }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<List<MediaBean>>(){
    @Override public void onComplete(){
    }
    @Override public void onError(    Throwable e){
      onGenerateMediaListener.onFinished(bucketId,page,limit,null);
    }
    @Override public void onNext(    List<MediaBean> mediaBeenList){
      onGenerateMediaListener.onFinished(bucketId,page,limit,mediaBeenList);
    }
  }
);
}
