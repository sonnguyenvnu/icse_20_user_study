@NativeType("CUresult") public static int cuSurfRefGetArray(@NativeType("CUarray *") PointerBuffer phArray,@NativeType("CUsurfref") long hSurfRef){
  if (CHECKS) {
    check(phArray,1);
  }
  return ncuSurfRefGetArray(memAddress(phArray),hSurfRef);
}
