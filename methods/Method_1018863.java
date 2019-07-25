@Override public void notify(MediaEventListener listener){
  listener.mediaSubItemAdded(component,temporaryMediaRef(newChild));
}
