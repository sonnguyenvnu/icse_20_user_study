@Override public void notify(MediaEventListener listener){
  listener.mediaFreed(component,temporaryMediaRef(md));
}
