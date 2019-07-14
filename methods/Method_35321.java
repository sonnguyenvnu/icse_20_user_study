@Override public Observable<List<PlayList>> playLists(){
  return mLocalDataSource.playLists().doOnNext(new Action1<List<PlayList>>(){
    @Override public void call(    List<PlayList> playLists){
      mCachedPlayLists=playLists;
    }
  }
);
}
