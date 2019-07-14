@Override public HttpUrlConnectionNetworkFetchState createFetchState(Consumer<EncodedImage> consumer,ProducerContext context){
  return new HttpUrlConnectionNetworkFetchState(consumer,context);
}
