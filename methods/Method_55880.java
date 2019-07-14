public static int ncuOccupancyMaxActiveBlocksPerMultiprocessorWithFlags(long numBlocks,long func,int blockSize,long dynamicSMemSize,int flags){
  long __functionAddress=Functions.OccupancyMaxActiveBlocksPerMultiprocessorWithFlags;
  if (CHECKS) {
    check(func);
  }
  return callPPPI(numBlocks,func,blockSize,dynamicSMemSize,flags,__functionAddress);
}
