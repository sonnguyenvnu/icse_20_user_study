protected void ensureNativeInstance(){
  if (nativeInst == 0) {
    throw new IllegalStateException("Native instance is not valid");
  }
}
