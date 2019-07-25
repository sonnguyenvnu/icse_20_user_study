@Override public Promise<TransformablePublisher<Event<?>>> request(URI uri,Action<? super RequestSpec> action){
  return httpClient.requestStream(uri,action).map(r -> new ServerSentEventDecodingPublisher(r.getBody(),httpClient.getByteBufAllocator()));
}
