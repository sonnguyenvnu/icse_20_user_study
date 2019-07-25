@Override public void notify(MediaPlayerEventListener listener){
  listener.seekableChanged(mediaPlayer,newSeekable);
}
