private void adjustPendingCells(boolean silent){
  if (mPendingCells.size() > 0) {
    Collections.sort(mPendingCells,CellPositionComparator.COMPARATOR);
    for (Iterator<BaseCell> iter=mPendingCells.iterator(); iter.hasNext(); ) {
      BaseCell next=iter.next();
      if (next.position < 0)       continue;
      if (next.position < mCells.size()) {
        mCells.add(next.position,next);
        mInQueueCells.add(next);
        iter.remove();
        if (!silent)         next.added();
      }
 else {
        break;
      }
    }
  }
  if (mInQueueCells.size() > 0) {
    Collections.sort(mInQueueCells,CellPositionComparator.REVERSE_COMPARATOR);
    for (Iterator<BaseCell> iter=mInQueueCells.iterator(); iter.hasNext(); ) {
      BaseCell next=iter.next();
      if (next.position < 0)       continue;
      if (next.position > mCells.size()) {
        mPendingCells.add(next);
        iter.remove();
      }
 else {
        break;
      }
    }
  }
  if (TangramBuilder.isPrintLog()) {
    if (mPendingCells.size() > 0 && mInQueueCells.size() > 0) {
      Preconditions.checkState(mPendingCells.get(0).position >= mInQueueCells.get(mInQueueCells.size() - 1).position,"Items in pendingQueue must have large position than Items in queue");
    }
  }
}
