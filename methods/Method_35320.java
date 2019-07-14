@Override public Observable<Song> setSongAsFavorite(final Song song,final boolean isFavorite){
  return Observable.create(new Observable.OnSubscribe<Song>(){
    @Override public void call(    Subscriber<? super Song> subscriber){
      List<PlayList> playLists=mLiteOrm.query(QueryBuilder.create(PlayList.class).whereEquals(PlayList.COLUMN_FAVORITE,String.valueOf(true)));
      if (playLists.isEmpty()) {
        PlayList defaultFavorite=DBUtils.generateFavoritePlayList(mContext);
        playLists.add(defaultFavorite);
      }
      PlayList favorite=playLists.get(0);
      song.setFavorite(isFavorite);
      favorite.setUpdatedAt(new Date());
      if (isFavorite) {
        favorite.addSong(song,0);
      }
 else {
        favorite.removeSong(song);
      }
      mLiteOrm.insert(song,ConflictAlgorithm.Replace);
      long result=mLiteOrm.insert(favorite,ConflictAlgorithm.Replace);
      if (result > 0) {
        subscriber.onNext(song);
      }
 else {
        if (isFavorite) {
          subscriber.onError(new Exception("Set song as favorite failed"));
        }
 else {
          subscriber.onError(new Exception("Set song as unfavorite failed"));
        }
      }
      subscriber.onCompleted();
    }
  }
);
}
