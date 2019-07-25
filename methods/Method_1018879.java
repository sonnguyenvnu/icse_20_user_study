@Override public void notify(MediaListEventListener listener){
  listener.mediaListWillAddItem(component,temporaryMediaRef(item),index);
}
