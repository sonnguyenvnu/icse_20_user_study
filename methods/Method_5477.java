@Override public long getBufferedPositionUs(){
  return loadingFinished ? C.TIME_END_OF_SOURCE : 0;
}
