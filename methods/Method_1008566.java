public void add(TranslogStats translogStats){
  if (translogStats == null) {
    return;
  }
  this.numberOfOperations+=translogStats.numberOfOperations;
  this.translogSizeInBytes+=translogStats.translogSizeInBytes;
  this.uncommittedOperations+=translogStats.uncommittedOperations;
  this.uncommittedSizeInBytes+=translogStats.uncommittedSizeInBytes;
}
