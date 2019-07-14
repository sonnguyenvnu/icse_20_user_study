@NativeType("CUresult") public static int cuOccupancyMaxActiveBlocksPerMultiprocessor(@NativeType("int *") IntBuffer numBlocks,@NativeType("CUfunction") long func,int blockSize,@NativeType("size_t") long dynamicSMemSize){
  if (CHECKS) {
    check(numBlocks,1);
  }
  return ncuOccupancyMaxActiveBlocksPerMultiprocessor(memAddress(numBlocks),func,blockSize,dynamicSMemSize);
}
