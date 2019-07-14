@Override public boolean playLast(){
  isPaused=false;
  boolean hasLast=mPlayList.hasLast();
  if (hasLast) {
    Song last=mPlayList.last();
    play();
    notifyPlayLast(last);
    return true;
  }
  return false;
}
