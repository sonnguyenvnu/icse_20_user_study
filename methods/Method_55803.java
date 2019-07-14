@NativeType("CUresult") public static int cuStreamQuery(@NativeType("CUstream") long hStream){
  long __functionAddress=Functions.StreamQuery;
  return callPI(hStream,__functionAddress);
}
