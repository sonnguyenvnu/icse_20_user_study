@NativeType("CUresult") public static int cuMemGetInfo(@NativeType("size_t *") PointerBuffer free,@NativeType("size_t *") PointerBuffer total){
  if (CHECKS) {
    check(free,1);
    check(total,1);
  }
  return ncuMemGetInfo(memAddress(free),memAddress(total));
}
