public static <E>JavaDStream<MessageAndMetadata<E>> launch(JavaStreamingContext jsc,Properties pros,int numberOfReceivers,StorageLevel storageLevel,KafkaMessageHandler<E> messageHandler){
  return createStream(jsc,pros,numberOfReceivers,storageLevel,messageHandler);
}
