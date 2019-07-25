@Override public void notify(MediaListEventListener listener){
  listener.mediaListItemDeleted(component,temporaryMediaRef(item),index);
}
