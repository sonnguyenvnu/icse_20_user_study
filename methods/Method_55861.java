@NativeType("CUresult") public static int cuMemHostUnregister(@NativeType("void *") ByteBuffer p){
  return ncuMemHostUnregister(memAddress(p));
}
