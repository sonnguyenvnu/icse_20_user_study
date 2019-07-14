public void incrementReceivedItemsCount(int networkType,int dataType,int value){
  receivedItems[networkType][dataType]+=value;
  saveStats();
}
