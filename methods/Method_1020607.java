public static DStream<MessageAndMetadata<byte[]>> launch(StreamingContext ssc,Properties pros,int numberOfReceivers,StorageLevel storageLevel){
  JavaStreamingContext jsc=new JavaStreamingContext(ssc);
  return createStream(jsc,pros,numberOfReceivers,storageLevel,new IdentityMessageHandler()).dstream();
}
