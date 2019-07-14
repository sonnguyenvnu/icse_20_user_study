boolean usesWriteEntries(){
  return usesWriteQueue() || recordsWrite();
}
