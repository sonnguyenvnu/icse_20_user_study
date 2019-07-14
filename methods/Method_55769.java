public static int ncuMemAlloc(long dptr,long bytesize){
  long __functionAddress=Functions.MemAlloc;
  return callPPI(dptr,bytesize,__functionAddress);
}
