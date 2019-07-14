protected static void notifyConsumer(PooledByteBufferOutputStream pooledOutputStream,@Consumer.Status int status,@Nullable BytesRange responseBytesRange,Consumer<EncodedImage> consumer){
  CloseableReference<PooledByteBuffer> result=CloseableReference.of(pooledOutputStream.toByteBuffer());
  EncodedImage encodedImage=null;
  try {
    encodedImage=new EncodedImage(result);
    encodedImage.setBytesRange(responseBytesRange);
    encodedImage.parseMetaData();
    consumer.onNewResult(encodedImage,status);
  }
  finally {
    EncodedImage.closeSafely(encodedImage);
    CloseableReference.closeSafely(result);
  }
}
