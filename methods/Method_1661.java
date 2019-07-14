/** 
 * Creates a memory-backed encoded image from the stream. The stream is closed. 
 */
protected EncodedImage getByteBufferBackedEncodedImage(InputStream inputStream,int length) throws IOException {
  CloseableReference<PooledByteBuffer> ref=null;
  try {
    if (length <= 0) {
      ref=CloseableReference.of(mPooledByteBufferFactory.newByteBuffer(inputStream));
    }
 else {
      ref=CloseableReference.of(mPooledByteBufferFactory.newByteBuffer(inputStream,length));
    }
    return new EncodedImage(ref);
  }
  finally {
    Closeables.closeQuietly(inputStream);
    CloseableReference.closeSafely(ref);
  }
}
