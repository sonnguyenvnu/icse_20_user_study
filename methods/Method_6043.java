@CallSuper @Override protected boolean flushOrReleaseCodec(){
  try {
    return super.flushOrReleaseCodec();
  }
  finally {
    buffersInCodecCount=0;
  }
}
