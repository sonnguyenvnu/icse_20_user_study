@Override public void refreshFolder(final Folder folder){
  Subscription subscription=Observable.just(FileUtils.musicFiles(new File(folder.getPath()))).flatMap(new Func1<List<Song>,Observable<Folder>>(){
    @Override public Observable<Folder> call(    List<Song> songs){
      folder.setSongs(songs);
      folder.setNumOfSongs(songs.size());
      return mRepository.update(folder);
    }
  }
).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Folder>(){
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
    @Override public void onNext(    Folder folder){
      mView.onFolderUpdated(folder);
    }
  }
);
  mSubscriptions.add(subscription);
}
