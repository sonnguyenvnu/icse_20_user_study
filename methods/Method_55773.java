public static int ncuMemGetAddressRange(long pbase,long psize,long dptr){
  long __functionAddress=Functions.MemGetAddressRange;
  if (CHECKS) {
    check(dptr);
  }
  return callPPPI(pbase,psize,dptr,__functionAddress);
}
