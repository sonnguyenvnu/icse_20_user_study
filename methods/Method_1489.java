private Producer<EncodedImage> newLocalThumbnailProducer(ThumbnailProducer<EncodedImage>[] thumbnailProducers){
  ThumbnailBranchProducer thumbnailBranchProducer=mProducerFactory.newThumbnailBranchProducer(thumbnailProducers);
  return mProducerFactory.newResizeAndRotateProducer(thumbnailBranchProducer,true,mImageTranscoderFactory);
}
