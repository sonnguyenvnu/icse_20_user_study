public static int ncuOccupancyMaxActiveBlocksPerMultiprocessor(long numBlocks,long func,int blockSize,long dynamicSMemSize){
  long __functionAddress=Functions.OccupancyMaxActiveBlocksPerMultiprocessor;
  if (CHECKS) {
    check(func);
  }
  return callPPPI(numBlocks,func,blockSize,dynamicSMemSize,__functionAddress);
}
