@Override public void addFolderToPlayList(final Folder folder,PlayList playList){
  if (folder.getSongs().isEmpty())   return;
  if (playList.isFavorite()) {
    for (    Song song : folder.getSongs()) {
      song.setFavorite(true);
    }
  }
  playList.addSong(folder.getSongs(),0);
  Subscription subscription=mRepository.update(playList).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<PlayList>(){
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
      RxBus.getInstance().post(new PlayListUpdatedEvent(playList));
    }
  }
);
  mSubscriptions.add(subscription);
}
