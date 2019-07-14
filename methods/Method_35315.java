@Override public Observable<List<PlayList>> playLists(){
  return Observable.create(new Observable.OnSubscribe<List<PlayList>>(){
    @Override public void call(    Subscriber<? super List<PlayList>> subscriber){
      List<PlayList> playLists=mLiteOrm.query(PlayList.class);
      if (playLists.isEmpty()) {
        PlayList playList=DBUtils.generateFavoritePlayList(mContext);
        long result=mLiteOrm.save(playList);
        Log.d(TAG,"Create default playlist(Favorite) with " + (result == 1 ? "success" : "failure"));
        playLists.add(playList);
      }
      subscriber.onNext(playLists);
      subscriber.onCompleted();
    }
  }
);
}
