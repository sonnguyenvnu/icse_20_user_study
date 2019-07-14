private void correctOffsets(int startIndex,int childIndexUpdate,int windowOffsetUpdate,int periodOffsetUpdate){
  windowCount+=windowOffsetUpdate;
  periodCount+=periodOffsetUpdate;
  for (int i=startIndex; i < mediaSourceHolders.size(); i++) {
    mediaSourceHolders.get(i).childIndex+=childIndexUpdate;
    mediaSourceHolders.get(i).firstWindowIndexInChild+=windowOffsetUpdate;
    mediaSourceHolders.get(i).firstPeriodIndexInChild+=periodOffsetUpdate;
  }
}
