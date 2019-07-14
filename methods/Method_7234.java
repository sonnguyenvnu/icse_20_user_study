public long getReceivedBytesCount(int networkType,int dataType){
  if (dataType == TYPE_MESSAGES) {
    return receivedBytes[networkType][TYPE_TOTAL] - receivedBytes[networkType][TYPE_FILES] - receivedBytes[networkType][TYPE_AUDIOS] - receivedBytes[networkType][TYPE_VIDEOS] - receivedBytes[networkType][TYPE_PHOTOS];
  }
  return receivedBytes[networkType][dataType];
}
