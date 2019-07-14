protected void onResponse(FetchState fetchState,InputStream responseData,int responseContentLength) throws IOException {
  final PooledByteBufferOutputStream pooledOutputStream;
  if (responseContentLength > 0) {
    pooledOutputStream=mPooledByteBufferFactory.newOutputStream(responseContentLength);
  }
 else {
    pooledOutputStream=mPooledByteBufferFactory.newOutputStream();
  }
  final byte[] ioArray=mByteArrayPool.get(READ_SIZE);
  try {
    int length;
    while ((length=responseData.read(ioArray)) >= 0) {
      if (length > 0) {
        pooledOutputStream.write(ioArray,0,length);
        maybeHandleIntermediateResult(pooledOutputStream,fetchState);
        float progress=calculateProgress(pooledOutputStream.size(),responseContentLength);
        fetchState.getConsumer().onProgressUpdate(progress);
      }
    }
    mNetworkFetcher.onFetchCompletion(fetchState,pooledOutputStream.size());
    handleFinalResult(pooledOutputStream,fetchState);
  }
  finally {
    mByteArrayPool.release(ioArray);
    pooledOutputStream.close();
  }
}
