private static boolean shouldProcess(EncodedImage encodedImage,@Consumer.Status int status){
  return BaseConsumer.isLast(status) || BaseConsumer.statusHasFlag(status,Consumer.IS_PLACEHOLDER) || EncodedImage.isValid(encodedImage);
}
