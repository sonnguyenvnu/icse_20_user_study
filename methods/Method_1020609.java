public static JavaDStream<MessageAndMetadata<byte[]>> launch(JavaStreamingContext jsc,Properties pros,int numberOfReceivers,StorageLevel storageLevel){
  return createStream(jsc,pros,numberOfReceivers,storageLevel,new IdentityMessageHandler());
}
