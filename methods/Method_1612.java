private static MemoryFile copyToMemoryFile(CloseableReference<PooledByteBuffer> bytesRef,int inputLength,@Nullable byte[] suffix) throws IOException {
  int outputLength=inputLength + (suffix == null ? 0 : suffix.length);
  MemoryFile memoryFile=new MemoryFile(null,outputLength);
  memoryFile.allowPurging(false);
  PooledByteBufferInputStream pbbIs=null;
  LimitedInputStream is=null;
  OutputStream os=null;
  try {
    pbbIs=new PooledByteBufferInputStream(bytesRef.get());
    is=new LimitedInputStream(pbbIs,inputLength);
    os=memoryFile.getOutputStream();
    ByteStreams.copy(is,os);
    if (suffix != null) {
      memoryFile.writeBytes(suffix,0,inputLength,suffix.length);
    }
    return memoryFile;
  }
  finally {
    CloseableReference.closeSafely(bytesRef);
    Closeables.closeQuietly(pbbIs);
    Closeables.closeQuietly(is);
    Closeables.close(os,true);
  }
}
