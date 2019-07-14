@NativeType("CUresult") public static int cuMipmappedArrayDestroy(@NativeType("CUmipmappedArray") long hMipmappedArray){
  long __functionAddress=Functions.MipmappedArrayDestroy;
  if (CHECKS) {
    check(hMipmappedArray);
  }
  return callPI(hMipmappedArray,__functionAddress);
}
