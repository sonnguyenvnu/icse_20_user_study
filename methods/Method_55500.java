private static void checkBuffer(int bufferSize,int minimumSize){
  if (bufferSize < minimumSize) {
    throwIAE(bufferSize,minimumSize);
  }
}
