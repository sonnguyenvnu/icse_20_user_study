public static int ncuGraphicsResourceGetMappedPointer(long pDevPtr,long pSize,long resource){
  long __functionAddress=Functions.GraphicsResourceGetMappedPointer;
  if (CHECKS) {
    check(resource);
  }
  return callPPPI(pDevPtr,pSize,resource,__functionAddress);
}
