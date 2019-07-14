@Override public long getNextLoadPositionUs(){
  return loadingFinished || loader.isLoading() ? C.TIME_END_OF_SOURCE : 0;
}
