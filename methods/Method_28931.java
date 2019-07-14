public long getMeanBorrowWaitTimeMillis(){
  if (poolInactive()) {
    return -1;
  }
  return this.internalPool.getMeanBorrowWaitTimeMillis();
}
