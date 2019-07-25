public <Q,R>AsyncFuture<R> request(final GrpcDescriptor<Q,R> endpoint,final Q entity,final CallOptions options){
  final Span rootSpan=tracer.getCurrentSpan();
  return channel.doto(channel -> {
    final byte[] body;
    try {
      body=mapper.writeValueAsBytes(entity);
    }
 catch (    JsonProcessingException e) {
      return async.failed(e);
    }
    final ClientCall<byte[],byte[]> call=channel.newCall(endpoint.descriptor(),options);
    final Metadata metadata=new Metadata();
    final ResolvableFuture<R> future=async.future();
    call.start(new ClientCall.Listener<byte[]>(){
      @Override public void onMessage(      final byte[] message){
        final R response;
        try {
          response=mapper.readValue(message,endpoint.responseType());
        }
 catch (        IOException e) {
          future.fail(e);
          return;
        }
        future.resolve(response);
      }
      @Override public void onClose(      final Status status,      final Metadata trailers){
        if (status.isOk()) {
          if (!future.isDone()) {
            future.fail(new RuntimeException("Request finished without response"));
          }
        }
 else {
          future.fail(new RuntimeException("Request finished with status code (" + status + ")"));
        }
      }
      @Override public void onHeaders(      final Metadata headers){
      }
      @Override public void onReady(){
      }
    }
,metadata);
    final Scope scope=tracer.withSpan(rootSpan);
    call.sendMessage(body);
    call.setMessageCompression(true);
    call.request(1);
    call.halfClose();
    return future.onFailed(e -> {
      if (!endpoint.descriptor().getFullMethodName().contains("heroic/suggest:")) {
        errors.mark();
      }
    }
).onFinished(scope::close);
  }
);
}
