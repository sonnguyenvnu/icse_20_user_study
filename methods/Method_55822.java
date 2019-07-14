@NativeType("CUresult") public static int cuTexRefSetFlags(@NativeType("CUtexref") long hTexRef,@NativeType("unsigned int") int Flags){
  long __functionAddress=Functions.TexRefSetFlags;
  if (CHECKS) {
    check(hTexRef);
  }
  return callPI(hTexRef,Flags,__functionAddress);
}
