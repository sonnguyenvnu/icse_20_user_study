@Override public void notify(MediaListEventListener listener){
  listener.mediaListItemAdded(component,temporaryMediaRef(item),index);
}
