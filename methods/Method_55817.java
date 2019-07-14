@NativeType("CUresult") public static int cuTexRefSetArray(@NativeType("CUtexref") long hTexRef,@NativeType("CUarray") long hArray,@NativeType("unsigned int") int Flags){
  long __functionAddress=Functions.TexRefSetArray;
  if (CHECKS) {
    check(hTexRef);
    check(hArray);
  }
  return callPPI(hTexRef,hArray,Flags,__functionAddress);
}
