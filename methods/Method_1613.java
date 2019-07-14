private Bitmap decodeFileDescriptorAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef,int inputLength,byte[] suffix,BitmapFactory.Options options){
  MemoryFile memoryFile=null;
  try {
    memoryFile=copyToMemoryFile(bytesRef,inputLength,suffix);
    FileDescriptor fd=getMemoryFileDescriptor(memoryFile);
    if (mWebpBitmapFactory != null) {
      Bitmap bitmap=mWebpBitmapFactory.decodeFileDescriptor(fd,null,options);
      return Preconditions.checkNotNull(bitmap,"BitmapFactory returned null");
    }
 else {
      throw new IllegalStateException("WebpBitmapFactory is null");
    }
  }
 catch (  IOException e) {
    throw Throwables.propagate(e);
  }
 finally {
    if (memoryFile != null) {
      memoryFile.close();
    }
  }
}
