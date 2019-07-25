@Override public void notify(MediaPlayerEventListener listener){
  listener.mediaChanged(mediaPlayer,new MediaRef(libvlcInstance,newMedia));
}
