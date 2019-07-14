public static int ncuDeviceTotalMem(long bytes,int dev){
  long __functionAddress=Functions.DeviceTotalMem;
  return callPI(bytes,dev,__functionAddress);
}
