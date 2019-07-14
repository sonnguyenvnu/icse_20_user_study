@NativeType("CUresult") public static int cuTexRefSetMipmappedArray(@NativeType("CUtexref") long hTexRef,@NativeType("CUmipmappedArray") long hMipmappedArray,@NativeType("unsigned int") int Flags){
  long __functionAddress=Functions.TexRefSetMipmappedArray;
  if (CHECKS) {
    check(hTexRef);
    check(hMipmappedArray);
  }
  return callPPI(hTexRef,hMipmappedArray,Flags,__functionAddress);
}
