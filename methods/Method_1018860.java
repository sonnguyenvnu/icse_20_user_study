@Override public void notify(MediaEventListener listener){
  listener.mediaMetaChanged(component,Meta.meta(metaType));
}
