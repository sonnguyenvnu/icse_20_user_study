@Override public void loadPlayLists(){
  Subscription subscription=mRepository.playLists().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<PlayList>>(){
    @Override public void onStart(){
      mView.showLoading();
    }
    @Override public void onCompleted(){
      mView.hideLoading();
    }
    @Override public void onError(    Throwable e){
      mView.hideLoading();
      mView.handleError(e);
    }
    @Override public void onNext(    List<PlayList> playLists){
      mView.onPlayListsLoaded(playLists);
    }
  }
);
  mSubscriptions.add(subscription);
}
