@Override public void notify(MediaPlayerEventListener listener){
  listener.elementaryStreamDeleted(mediaPlayer,TrackType.trackType(type),id);
}
