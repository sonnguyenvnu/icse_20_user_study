@Override public void shutDown(){
  GlideApp.get(getContext()).clearMemory();
}
