public PooledByteStreams getPooledByteStreams(){
  if (mPooledByteStreams == null) {
    mPooledByteStreams=new PooledByteStreams(getSmallByteArrayPool());
  }
  return mPooledByteStreams;
}
