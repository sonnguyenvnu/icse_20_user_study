@NativeType("CUresult") public static int cuInit(@NativeType("unsigned int") int Flags){
  long __functionAddress=Functions.Init;
  return callI(Flags,__functionAddress);
}
