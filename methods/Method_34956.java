@SuppressWarnings("CheckReturnValue") public void loadPosts(){
  BarCode awwRequest=new BarCode(RedditData.class.getSimpleName(),"aww");
  this.persistedStore.get(awwRequest).flatMapObservable(this::sanitizeData).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::showPosts,throwable -> Log.e(StoreActivity.class.getSimpleName(),throwable.getMessage(),throwable));
}
