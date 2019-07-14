private void updateProgress(long count){
  bytesWritten+=count;
  progressHandler.sendProgressMessage(bytesWritten,totalSize);
}
