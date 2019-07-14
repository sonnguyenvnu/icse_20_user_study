@NativeType("CUresult") public static int cuTexRefSetMipmapFilterMode(@NativeType("CUtexref") long hTexRef,@NativeType("CUfilter_mode") int fm){
  long __functionAddress=Functions.TexRefSetMipmapFilterMode;
  if (CHECKS) {
    check(hTexRef);
  }
  return callPI(hTexRef,fm,__functionAddress);
}
