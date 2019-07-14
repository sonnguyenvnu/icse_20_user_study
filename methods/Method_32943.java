@Override protected int computeVerticalScrollOffset(){
  return mEmulator == null ? 1 : mEmulator.getScreen().getActiveRows() + mTopRow - mEmulator.mRows;
}
