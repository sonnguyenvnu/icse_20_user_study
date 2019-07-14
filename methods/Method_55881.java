public static int ncuOccupancyMaxPotentialBlockSizeWithFlags(long minGridSize,long blockSize,long func,long blockSizeToDynamicSMemSize,long dynamicSMemSize,int blockSizeLimit,int flags){
  long __functionAddress=Functions.OccupancyMaxPotentialBlockSizeWithFlags;
  if (CHECKS) {
    check(func);
  }
  return callPPPPPI(minGridSize,blockSize,func,blockSizeToDynamicSMemSize,dynamicSMemSize,blockSizeLimit,flags,__functionAddress);
}
