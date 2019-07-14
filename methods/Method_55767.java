public static int ncuMemGetInfo(long free,long total){
  long __functionAddress=Functions.MemGetInfo;
  return callPPI(free,total,__functionAddress);
}
