public void incrementSentBytesCount(int networkType,int dataType,long value){
  sentBytes[networkType][dataType]+=value;
  saveStats();
}
