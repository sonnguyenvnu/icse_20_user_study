@SuppressWarnings("CheckReturnValue") public void loadPosts(){
  BarCode awwRequest=new BarCode(RedditData.class.getSimpleName(),"aww");
  this.nonPersistedStore.get(awwRequest).flatMapObservable(new Function<RedditData,ObservableSource<Post>>(){
    @Override public ObservableSource<Post> apply(    @NonNull RedditData redditData) throws Exception {
      return sanitizeData(redditData);
    }
  }
).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(this::showPosts,throwable -> {
    Log.e(StoreActivity.class.getSimpleName(),throwable.getMessage(),throwable);
  }
);
}
