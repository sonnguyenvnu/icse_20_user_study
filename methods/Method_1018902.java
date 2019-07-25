@Override public void notify(MediaPlayerEventListener listener){
  listener.elementaryStreamAdded(mediaPlayer,TrackType.trackType(type),id);
}
