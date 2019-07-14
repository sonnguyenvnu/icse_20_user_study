public long getSentBytesCount(int networkType,int dataType){
  if (dataType == TYPE_MESSAGES) {
    return sentBytes[networkType][TYPE_TOTAL] - sentBytes[networkType][TYPE_FILES] - sentBytes[networkType][TYPE_AUDIOS] - sentBytes[networkType][TYPE_VIDEOS] - sentBytes[networkType][TYPE_PHOTOS];
  }
  return sentBytes[networkType][dataType];
}
