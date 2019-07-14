@Override public final Object getUidOfPeriod(int periodIndex){
  int childIndex=getChildIndexByPeriodIndex(periodIndex);
  int firstPeriodIndexInChild=getFirstPeriodIndexByChildIndex(childIndex);
  Object periodUidInChild=getTimelineByChildIndex(childIndex).getUidOfPeriod(periodIndex - firstPeriodIndexInChild);
  return getConcatenatedUid(getChildUidByChildIndex(childIndex),periodUidInChild);
}
