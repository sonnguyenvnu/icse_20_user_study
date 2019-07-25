@Override public void notify(MediaEventListener listener){
  listener.mediaStateChanged(component,State.state(newState));
}
