@Override protected int computeVerticalScrollRange(){
  return mEmulator == null ? 1 : mEmulator.getScreen().getActiveRows();
}
