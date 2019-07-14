@Override public void generateBuckets(){
  Observable.create((ObservableOnSubscribe<List<BucketBean>>)subscriber -> {
    List<BucketBean> bucketBeanList=null;
    if (isImage) {
      bucketBeanList=MediaUtils.getAllBucketByImage(context);
    }
 else {
      bucketBeanList=MediaUtils.getAllBucketByVideo(context);
    }
    subscriber.onNext(bucketBeanList);
    subscriber.onComplete();
  }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<List<BucketBean>>(){
    @Override public void onComplete(){
    }
    @Override public void onError(    Throwable e){
      onGenerateBucketListener.onFinished(null);
    }
    @Override public void onNext(    List<BucketBean> bucketBeanList){
      onGenerateBucketListener.onFinished(bucketBeanList);
    }
  }
);
}
