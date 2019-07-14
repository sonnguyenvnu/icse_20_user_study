private void computeRange(int firstVisible,int lastVisible){
  final int rangeSize;
  final int rangeStart;
  final int rangeEnd;
  final int treeHoldersSize;
synchronized (this) {
    if (!isMeasured() || mEstimatedViewportCount == UNSET) {
      return;
    }
    if (firstVisible == RecyclerView.NO_POSITION || lastVisible == RecyclerView.NO_POSITION) {
      firstVisible=lastVisible=0;
    }
    rangeSize=Math.max(mEstimatedViewportCount,lastVisible - firstVisible);
    treeHoldersSize=mComponentTreeHolders.size();
    if (mIsCircular) {
      rangeStart=0;
      rangeEnd=treeHoldersSize;
    }
 else {
      rangeStart=firstVisible - (int)(rangeSize * mRangeRatio);
      rangeEnd=firstVisible + rangeSize + (int)(rangeSize * mRangeRatio);
    }
  }
  mRangeTraverser.traverse(0,treeHoldersSize,firstVisible,lastVisible,new RecyclerRangeTraverser.Processor(){
    @Override public boolean process(    int index){
      return computeRangeLayoutAt(index,rangeStart,rangeEnd,treeHoldersSize);
    }
  }
);
}
