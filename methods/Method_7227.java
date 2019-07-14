public void incrementSentItemsCount(int networkType,int dataType,int value){
  sentItems[networkType][dataType]+=value;
  saveStats();
}
