@Override public void notify(MediaPlayerEventListener listener){
  listener.elementaryStreamSelected(mediaPlayer,TrackType.trackType(type),id);
}
