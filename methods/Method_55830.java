public static int ncuStreamIsCapturing(long hStream,long captureStatus){
  long __functionAddress=Functions.StreamIsCapturing;
  return callPPI(hStream,captureStatus,__functionAddress);
}
