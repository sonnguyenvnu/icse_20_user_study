@NativeType("CUresult") public static int cuParamSetv(@NativeType("CUfunction") long hfunc,int offset,@NativeType("void *") ByteBuffer ptr){
  return ncuParamSetv(hfunc,offset,memAddress(ptr),ptr.remaining());
}
