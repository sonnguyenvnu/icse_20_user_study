@Override public void notify(MediaListEventListener listener){
  listener.mediaListWillDeleteItem(component,temporaryMediaRef(item),index);
}
