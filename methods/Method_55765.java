@NativeType("CUresult") public static int cuModuleGetGlobal(@Nullable @NativeType("CUdeviceptr *") PointerBuffer dptr,@Nullable @NativeType("size_t *") PointerBuffer bytes,@NativeType("CUmodule") long hmod,@NativeType("char const *") ByteBuffer name){
  if (CHECKS) {
    checkSafe(dptr,1);
    checkSafe(bytes,1);
    checkNT1(name);
  }
  return ncuModuleGetGlobal(memAddressSafe(dptr),memAddressSafe(bytes),hmod,memAddress(name));
}
