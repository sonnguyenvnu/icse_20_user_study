private static void incrementalCopyFastPath(byte[] output,int srcIndex,int opIndex,int length){
  int copiedLength=0;
  while ((opIndex + copiedLength) - srcIndex < 8) {
    copyLong(output,srcIndex,output,opIndex + copiedLength);
    copiedLength+=(opIndex + copiedLength) - srcIndex;
  }
  for (int i=0; i < length - copiedLength; i+=8) {
    copyLong(output,srcIndex + i,output,opIndex + copiedLength + i);
  }
}
