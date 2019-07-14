@Override public Observable<List<Song>> insert(final List<Song> songs){
  return Observable.create(new Observable.OnSubscribe<List<Song>>(){
    @Override public void call(    Subscriber<? super List<Song>> subscriber){
      for (      Song song : songs) {
        mLiteOrm.insert(song,ConflictAlgorithm.Abort);
      }
      List<Song> allSongs=mLiteOrm.query(Song.class);
      File file;
      for (Iterator<Song> iterator=allSongs.iterator(); iterator.hasNext(); ) {
        Song song=iterator.next();
        file=new File(song.getPath());
        boolean exists=file.exists();
        if (!exists) {
          iterator.remove();
          mLiteOrm.delete(song);
        }
      }
      subscriber.onNext(allSongs);
      subscriber.onCompleted();
    }
  }
);
}
