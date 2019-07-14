@NativeType("CUresult") public static int cuStreamSynchronize(@NativeType("CUstream") long hStream){
  long __functionAddress=Functions.StreamSynchronize;
  return callPI(hStream,__functionAddress);
}
