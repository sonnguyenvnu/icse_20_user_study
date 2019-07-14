public WebpTranscodeProducer newWebpTranscodeProducer(Producer<EncodedImage> inputProducer){
  return new WebpTranscodeProducer(mExecutorSupplier.forBackgroundTasks(),mPooledByteBufferFactory,inputProducer);
}
