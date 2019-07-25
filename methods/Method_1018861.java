@Override public void notify(MediaEventListener listener){
  listener.mediaParsedChanged(component,MediaParsedStatus.mediaParsedStatus(newStatus));
}
