public long[] getStatistics(){
  return new long[]{successTxCounter.get(),failureTxCounter.get()};
}
