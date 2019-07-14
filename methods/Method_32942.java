@Override protected int computeVerticalScrollExtent(){
  return mEmulator == null ? 1 : mEmulator.mRows;
}
