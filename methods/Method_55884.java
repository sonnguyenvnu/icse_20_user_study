@NativeType("CUresult") public static int cuPointerGetAttributes(@NativeType("CUpointer_attribute *") IntBuffer attributes,@NativeType("void **") PointerBuffer data,@NativeType("CUdeviceptr") long ptr){
  if (CHECKS) {
    check(data,attributes.remaining());
  }
  return ncuPointerGetAttributes(attributes.remaining(),memAddress(attributes),memAddress(data),ptr);
}
