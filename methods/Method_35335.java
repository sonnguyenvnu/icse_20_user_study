private RemoteViews getSmallContentView(){
  if (mContentViewSmall == null) {
    mContentViewSmall=new RemoteViews(getPackageName(),R.layout.remote_view_music_player_small);
    setUpRemoteView(mContentViewSmall);
  }
  updateRemoteViews(mContentViewSmall);
  return mContentViewSmall;
}
