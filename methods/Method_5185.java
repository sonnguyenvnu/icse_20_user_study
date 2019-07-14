private void moveMediaSourceInternal(int currentIndex,int newIndex){
  int startIndex=Math.min(currentIndex,newIndex);
  int endIndex=Math.max(currentIndex,newIndex);
  int windowOffset=mediaSourceHolders.get(startIndex).firstWindowIndexInChild;
  int periodOffset=mediaSourceHolders.get(startIndex).firstPeriodIndexInChild;
  mediaSourceHolders.add(newIndex,mediaSourceHolders.remove(currentIndex));
  for (int i=startIndex; i <= endIndex; i++) {
    MediaSourceHolder holder=mediaSourceHolders.get(i);
    holder.firstWindowIndexInChild=windowOffset;
    holder.firstPeriodIndexInChild=periodOffset;
    windowOffset+=holder.timeline.getWindowCount();
    periodOffset+=holder.timeline.getPeriodCount();
  }
}
