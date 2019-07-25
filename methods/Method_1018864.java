@Override public void notify(MediaEventListener listener){
  listener.mediaSubItemTreeAdded(component,temporaryMediaRef(item));
}
