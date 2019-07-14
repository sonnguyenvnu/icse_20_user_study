public static int ncuMemAllocPitch(long dptr,long pPitch,long WidthInBytes,long Height,int ElementSizeBytes){
  long __functionAddress=Functions.MemAllocPitch;
  return callPPPPI(dptr,pPitch,WidthInBytes,Height,ElementSizeBytes,__functionAddress);
}
