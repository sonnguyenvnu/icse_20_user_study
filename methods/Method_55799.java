public static int ncuArrayCreate(long pHandle,long pAllocateArray){
  long __functionAddress=Functions.ArrayCreate;
  return callPPI(pHandle,pAllocateArray,__functionAddress);
}
