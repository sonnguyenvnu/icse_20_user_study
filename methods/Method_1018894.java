@Override public void notify(MediaPlayerEventListener listener){
  listener.audioDeviceChanged(mediaPlayer,device);
}
