public static AddImageTransformMetaDataProducer newAddImageTransformMetaDataProducer(Producer<EncodedImage> inputProducer){
  return new AddImageTransformMetaDataProducer(inputProducer);
}
