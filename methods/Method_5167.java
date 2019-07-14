@Override public boolean continueLoading(long positionUs){
  boolean madeProgress=false;
  boolean madeProgressThisIteration;
  do {
    madeProgressThisIteration=false;
    long nextLoadPositionUs=getNextLoadPositionUs();
    if (nextLoadPositionUs == C.TIME_END_OF_SOURCE) {
      break;
    }
    for (    SequenceableLoader loader : loaders) {
      long loaderNextLoadPositionUs=loader.getNextLoadPositionUs();
      boolean isLoaderBehind=loaderNextLoadPositionUs != C.TIME_END_OF_SOURCE && loaderNextLoadPositionUs <= positionUs;
      if (loaderNextLoadPositionUs == nextLoadPositionUs || isLoaderBehind) {
        madeProgressThisIteration|=loader.continueLoading(positionUs);
      }
    }
    madeProgress|=madeProgressThisIteration;
  }
 while (madeProgressThisIteration);
  return madeProgress;
}
