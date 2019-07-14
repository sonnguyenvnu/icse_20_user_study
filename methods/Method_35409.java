@Override public void createPlayList(PlayList playList){
  Subscription subscription=mRepository.create(playList).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<PlayList>(){
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
    @Override public void onNext(    PlayList playList){
      mView.onPlayListCreated(playList);
    }
  }
);
  mSubscriptions.add(subscription);
}
