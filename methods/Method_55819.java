@NativeType("CUresult") public static int cuTexRefSetFormat(@NativeType("CUtexref") long hTexRef,@NativeType("CUarray_format") int fmt,int NumPackedComponents){
  long __functionAddress=Functions.TexRefSetFormat;
  if (CHECKS) {
    check(hTexRef);
  }
  return callPI(hTexRef,fmt,NumPackedComponents,__functionAddress);
}
