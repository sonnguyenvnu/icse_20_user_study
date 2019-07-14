@NativeType("CUresult") public static int cuSurfRefSetArray(@NativeType("CUsurfref") long hSurfRef,@NativeType("CUarray") long hArray,@NativeType("unsigned int") int Flags){
  long __functionAddress=Functions.SurfRefSetArray;
  if (CHECKS) {
    check(hSurfRef);
    check(hArray);
  }
  return callPPI(hSurfRef,hArray,Flags,__functionAddress);
}
