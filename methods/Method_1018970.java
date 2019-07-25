@Override public void notify(MediaListPlayerEventListener listener){
  listener.nextItem(component,temporaryMediaRef(item));
}
