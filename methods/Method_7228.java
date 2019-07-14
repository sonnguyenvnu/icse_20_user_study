public void incrementReceivedBytesCount(int networkType,int dataType,long value){
  receivedBytes[networkType][dataType]+=value;
  saveStats();
}
