public static int ncuMemHostAlloc(long pp,long bytesize,int Flags){
  long __functionAddress=Functions.MemHostAlloc;
  return callPPI(pp,bytesize,Flags,__functionAddress);
}
