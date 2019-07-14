@NativeType("CUresult") public static int cuTexRefSetMaxAnisotropy(@NativeType("CUtexref") long hTexRef,@NativeType("unsigned int") int maxAniso){
  long __functionAddress=Functions.TexRefSetMaxAnisotropy;
  if (CHECKS) {
    check(hTexRef);
  }
  return callPI(hTexRef,maxAniso,__functionAddress);
}
